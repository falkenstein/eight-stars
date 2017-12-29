package cz.tadea.player.action

import cz.tadea.player.Player
import cz.tadea.tactical.creature.CreatureTactical
import cz.tadea.ability.Ability
import cz.tadea.tactical.battlefield.BattlefieldZone

/**
 * Order given to a creature in tactical arena.
 */
class TacticalOrder(
        player: Player,
        val creature: CreatureTactical,
        val ability: Ability,
        val target: BattlefieldZone?
) : Action(player) {
}