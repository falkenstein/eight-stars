package cz.tadea.creature.enums

/**
 * Defines how much damage of the given type the creature can resist.
 */
enum class EDamageResistanceType(
        val flatReduction: Int = 0,
        /**
         * Can also function as increase when negative.
         */
        val percentageReduction: Int = 0
) {
    HELPLESS(percentageReduction = -100),
    WEAK(percentageReduction = -50),
    RESISTANT(flatReduction = 10),
    VERY_RESISTANT(flatReduction = 20),
    IMMUNE(percentageReduction = 100),
}