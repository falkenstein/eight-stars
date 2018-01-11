package cz.tadea.ability

/**
 * Defines priority for given ability. Ability priority is superior to creature initiative.
 */
enum class EAbilityPriority {
    /**
     * These come first before anything else. Defend is an example here.
     */
    PREEMPTIVE,
    /**
     * Used for abilities that should be used before others. Command abilities are good example here.
     */
    HIGH,
    /**
     * Executed normally, e.g. Attack or Move. Most abilities fit here.
     */
    NORMAL
}