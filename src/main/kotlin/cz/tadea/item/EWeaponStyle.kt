package main.kotlin.cz.tadea.item

/**
 * Defines how the weapon is wielded.
 */
enum class EWeaponStyle(
        val twoHanded: Boolean = true,
        val shield: Boolean = false
) {
    SINGLE(twoHanded = false),
    SHIELD(shield = true),
    DUAL,
    DOUBLE,
    GREAT,
    RANGED
}