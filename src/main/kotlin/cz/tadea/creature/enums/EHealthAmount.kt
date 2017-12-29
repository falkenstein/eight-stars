package cz.tadea.creature.enums

/**
 * Determines the amount of HP this creature has. Used for better clarification of creature table.
 */
enum class EHealthAmount(
        val hp: Int
) {
    MINOR(150),
    SMALL(200),
    MODERATE(250),
    HIGH(300),
    VERY_HIGH(400)
}