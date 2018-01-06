package cz.tadea.creature.enums

/**
 * Determines the amount of HP this creature has. Used for better clarification of creature table.
 */
enum class EHealthAmount(
        val hp: Int
) {
    MINOR(75),
    SMALL(100),
    MODERATE(125),
    HIGH(150),
    VERY_HIGH(200)
}