package cz.tadea.tactical.battle

import cz.tadea.ability.Ability
import cz.tadea.ability.EAbilityPriority
import cz.tadea.creature.company.Company
import cz.tadea.player.Player
import cz.tadea.tactical.battlefield.Battlefield
import cz.tadea.tactical.creature.CreatureTactical

/**
 * Represents flow of the battle and processes player input.
 */
class Battle(
        val players: Set<Player>,
        val companies: Map<Player, Company>
) {
    val battlefield: Battlefield = Battlefield(players)
    var turn: Int = 1

    init {
        players.forEach { player -> battlefield.initializeCompany(companies[player]!!) }
    }

    /**
     * Triggered after all actions are planned for all creatures.
     */
    fun evaluateTurn() {
        // First set default actions for all creatures that have no action to perform.
        battlefield.sides.values.forEach { side -> side.selectDefaultAbilities() }

        // Now evaluate all abilities by their order. The ones with preemptive priority come first.
        val preemptiveCreatures = getCreaturesWithSelectedAbilityByPriority(EAbilityPriority.PREEMPTIVE)
        executeSelectedAbilitiesForCreatures(preemptiveCreatures)

        // High priority now
        val highPriorityCreatures = getCreaturesWithSelectedAbilityByPriority(EAbilityPriority.HIGH)
        executeSelectedAbilitiesForCreatures(highPriorityCreatures)

        // Standard priority
        val standardPriorityCreatures = getCreaturesWithSelectedAbilityByPriority(EAbilityPriority.NORMAL)
        executeSelectedAbilitiesForCreatures(standardPriorityCreatures)

        battlefield.onEndTurn()
    }

    private fun getCreaturesWithSelectedAbilityByPriority(priority: EAbilityPriority): List<CreatureTactical> {
        return battlefield.sides.values.flatMap { side ->
            side.getCreaturesThatHaveSelectedAbilityWithPriority(priority)
        }
    }

    private fun executeSelectedAbilitiesForCreatures(selectedCreatures: List<CreatureTactical>) {
        for (creature in selectedCreatures) {
            if (creature.alive) { // No point in doing anything if the creature got killed.
                val selectedAbility: Ability = battlefield.sides[creature.owner]!!.getAbilitySelectedByCreature(creature)
            }
        }
    }

    /**
     * Determines the victorious player, if any. Most common result is empty set - the battle continues. If single player, then one winner.
     * Both players in the set mean draw.
     */
    fun getWinner(): Set<Player> {
        val winners = mutableSetOf<Player>()
        for (player in players) {
            if (battlefield.sides[player]!!.getAllLivingCreatures().isEmpty()) {
                winners.add(getOpponent(player))
            }
        }
        return winners
    }

    /**
     * Returns enemy of the given player.
     */
    fun getOpponent(player: Player): Player {
        return players.filter { pl -> pl != player }.first()
    }
}