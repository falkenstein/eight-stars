package main.kotlin.cz.tadea.tactical.battle

import main.kotlin.cz.tadea.ability.Ability
import main.kotlin.cz.tadea.ability.EAbility
import main.kotlin.cz.tadea.creature.company.Company
import main.kotlin.cz.tadea.player.Player
import main.kotlin.cz.tadea.tactical.battlefield.Battlefield
import main.kotlin.cz.tadea.tactical.creature.CreatureTactical

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
        players.first().initiative = true // Set the initiative to the first player.
    }

    /**
     * Triggered after all actions are planned for all creatures.
     */
    fun evaluateTurn() {
        // AI case
        composeActionOrderForAIPlayers()
        // First compose the action queue.
        val creaturesToActInOrder = composeActionOrder(battlefield)
        // Now execute the actions in their proper order.
        executeSelectedAbilitiesForCreatures(creaturesToActInOrder)
        // Switch initiative.
        switchInitiative()
        // End turn - auto-evaluate all behavior.
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
        for (i in 1 until maxOf(fastOrder.size, slowOrder.size)) {
            if (fastOrder.size >= i + 1) {
                actionOrder.add(fastOrder[i].first)
            }
            if (slowOrder.size >= i + 2) {
                actionOrder.add(slowOrder[i + 1].first)
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
                val targetZone = if (selectedAbility.associatedEnum == EAbility.MOVE) battlefield.sides[creature.owner]!!.getTargetOfMoveForCreature(creature) else null
                if (selectedAbility.canBeUsed()) {
                    // Now do the execution itself.
                    battlefield.useAbility(
                            creature = creature, abilityEnum = selectedAbility.associatedEnum, targetZone = targetZone
                    )
                }
            }
        }
    }

    private fun switchInitiative() {
        val playerWithInitiative = players.find { it.initiative == true } ?: throw IllegalStateException("No player has initiative")
        playerWithInitiative.initiative = false
        getOpponent(playerWithInitiative).initiative = true
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

    /**
     * AI behavior. ATM only selects default abilities.
     */
    fun composeActionOrderForAIPlayers() {
        for (player in players.filter { it.ai != null }) {
            battlefield.sides[player]?.selectDefaultAbilities()
        }
    }
}