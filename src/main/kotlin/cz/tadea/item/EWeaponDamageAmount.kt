package cz.tadea.item

/**
 * Defines damage range of the weapon. This class exists to make the weapons table clearer. Also avoids duplicity for damage numbers.
 */
enum class EWeaponDamageAmount(
        val damage: Int
) {
    MINOR(15),
    SMALL(20),
    MODERATE(25),
    HIGH(30),
    VERY_HIGH(40)
}