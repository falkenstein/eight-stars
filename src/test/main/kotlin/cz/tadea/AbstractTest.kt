package main.kotlin.cz.tadea

import main.kotlin.cz.tadea.player.Player
import main.kotlin.cz.tadea.ai.AI

/**
 * Abstract test for all other tests to inherit from. Significantly reduces code overhead.
 */
abstract class AbstractTest {
    val playerA = Player(0, "player A", AI())
    val playerB = Player(1, "player B", AI())
    val players: Set<Player> = setOf(playerA, playerB)
}