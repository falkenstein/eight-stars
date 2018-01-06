package cz.tadea.tactical.battlefield

import cz.tadea.tactical.creature.CreatureTactical

/**
 * A single zone on the battlefield that can contain a creature.
 */
class BattlefieldZone(
        /**
         * X coordinate of the zone. 0 means front, 1 means back.
         */
        val x: Int,
        /**
         * Y coordinate of the zone. 0 means leftmost position, 5 rightmost position.
         */
        val y: Int
) {
    var creature: CreatureTactical? = null

    /**
     * Determines whether it is possible to move unit into this zone (or through it).
     * TODO: Add other movement hinders once they are implemented.
     */
    fun canBeMovedInto(): Boolean {
        return creature == null
    }

    fun isFront(): Boolean {
        return y == 0
    }

    /**
     * Called when a creature enters this zone. Usually handles with environmental effects such as quicksand.
     */
    fun onCreatureEntered() {
    }

    override fun toString(): String {
        return "[$x,$y]"
    }
}