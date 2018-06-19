package main.kotlin.cz.tadea.tactical

/**
 * Possible types of damage. Weapons inflict physical damage, spells from elemental and light schools mostly elemental damage, spiritual
 * and shadow abilities mostly do spiritual damage (but shadow sometimes inflicts primal). Natural abilities do primal damage, as does poison.
 * Psionic abilities inflict special mental damage category.
 */
enum class EDamageType {
    /**
     * Caused by most weapons.
     */
    PHYSICAL,
    /**
     * Usually comes from psionic origin, rather rare.
     */
    MENTAL,
    /**
     * Single damage type for all ice, fire and storm abilities.
     */
    ELEMENTAL,
    /**
     * Poison and natural abilities.
     */
    PRIMAL,
    /**
     * Comes from spiritual spells and abilities.
     */
    SPIRITUAL,
}