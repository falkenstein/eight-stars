package cz.tadea.creature.enums

/**
 * Flags for creatures. They are used to determined modifiers such as mounted.
 */
enum class ECreatureFlag {
    /**
     * Runners move as fast as cavalry does, but they still count as infantry or archers.
     */
    RUNNER,
    /**
     * The creature is defending.
     */
    DEFEND
}