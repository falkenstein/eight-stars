package cz.tadea.player

import main.kotlin.cz.tadea.ai.AI

/**
 * Represents the player - either human or AI.
 */
class Player(
        val id: Int,
        val name: String,
        /**
         * AI behaves for the player if this field is defined.
         */
        val ai: AI? = null
) {
    var initiative: Boolean = false
}