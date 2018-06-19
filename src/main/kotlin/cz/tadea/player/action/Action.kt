package main.kotlin.cz.tadea.player.action

import main.kotlin.cz.tadea.player.Player

/**
 * Represents an action taken by a player. Can be used to reconstruct all player's actions.
 */
abstract class Action(
        val player: Player
) {
}