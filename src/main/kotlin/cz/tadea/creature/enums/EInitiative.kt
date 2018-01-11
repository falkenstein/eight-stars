package cz.tadea.creature.enums

/**
 * Determines how soon the creature will act.
 */
enum class EInitiative(
        val value: Int
) {
    EXTREMELY_SLOW(50),
    VERY_SLOW(75),
    SLOW(100),
    NORMAL(125),
    FAST(150),
    VERY_FAST(175),
    EXTREMELY_FAST(200);
}