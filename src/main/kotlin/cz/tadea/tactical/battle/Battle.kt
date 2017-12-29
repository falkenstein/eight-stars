package cz.tadea.tactical.battle

import cz.tadea.player.Player
import cz.tadea.tactical.battlefield.Battlefield

/**
 * Represents flow of the battle and processes player input.
 */
class Battle(
        val players: Set<Player>
) {
    val battlefield: Battlefield = Battlefield(players)
}