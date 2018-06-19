package cz.tadea.tactical.battle

import cz.tadea.ability.Ability
import cz.tadea.ability.EAbility
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
        // First compose the action queue.
        val creaturesToActInOrder = composeActionOrder(battlefield)
        // Now execute the actions in their proper order.
        executeSelectedAbilitiesForCreatures(creaturesToActInOrder)

        battlefield.onEndTurn()
    }

    /**
     * Properly interleaves the action orders from both teams.
     */
    protected fun composeActionOrder(battlefield: Battlefield): List<CreatureTactical> {
        val playerWithInitiative = players.find { it.initiative } ?: throw IllegalStateException("No player has initiative.")
        val fastOrder = battlefield.sides[playerWithInitiative]!!.selectedAbilities
        val slowOrder = battlefield.sides[getOpponent(playerWithInitiative)]!!.selectedAbilities
        val actionOrder = mutableListOf<CreatureTactical>()
        actionOrder.add(fastOrder[0].first)
        actionOrder.add(slowOrder[0].first)
        if (slowOrder.size > 1) {
            actionOrder.add(slowOrder[1].first) // Action order is first creature from the initiative side followed by two creatures from the other side.
        }
        for (i in 1..maxOf(fastOrder.size, slowOrder.size)) {
            if (fastOrder.size > i) {
                actionOrder.add(fastOrder[i].first)
            }
            if (slowOrder.size > i + 1) {
                actionOrder.add(slowOrder[i + i].first)
            }
        }
        return actionOrder
    }

    /**
     * Handles execution of abilities in the selected order.
     */
    private fun executeSelectedAbilitiesForCreatures(selectedCreatures: List<CreatureTactical>) {
        for (creature in selectedCreatures) {
            if (creature.alive) { // No point in doing anything if the creature got killed.
                val selectedAbility: Ability = battlefield.sides[creature.owner]!!.getAbilitySelectedByCreature(creature)
                        ?: creature.getAbility(EAbility.DEFEND) // Defend is fallback if the creature for some reason has no selected ability.
                if (selectedAbility.canBeUsed()) {
                    // Now do the execution itself.
                }
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
        return players.first { pl -> pl != player }
    }
}