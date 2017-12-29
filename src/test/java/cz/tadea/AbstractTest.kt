package cz.tadea

import cz.tadea.player.Player

/**
 * Abstract test for all other tests to inherit from. Significantly reduces code overhead.
 */
abstract class AbstractTest {
    val playerA = Player(0, "player A")
    val playerB = Player(1, "player B")
    val players: Set<Player> = setOf(playerA, playerB)
}