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
        val preemptiveCreatures: List<CreatureTactical> = battlefield.sides.values.flatMap { side ->
            side.getCreaturesThatHaveSelectedAbilityWithPriority(EAbilityPriority.PREEMPTIVE)
        }.sortedByDescending { creature -> creature.getInitiative() }
    }

    private fun executeSelectedAbilitiesForCreatures(selectedCreatures: List<CreatureTactical>) {
        for (creature in selectedCreatures) {
            if (creature.alive) { // No point in doing anything if the creature got killed.
                val selectedAbility: Ability = battlefield.sides[creature.owner]!!.getAbilitySelectedByCreature(creature)
            }
        }
    }
}