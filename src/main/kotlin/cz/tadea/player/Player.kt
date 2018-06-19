package cz.tadea.player

/**
 * Represents the player - either human or AI.
 */
class Player(
        val id: Int,
        val name: String
) {
    var initiative: Boolean = false
}