package cz.tadea.item

import cz.tadea.tactical.EDamageType

enum class EWeaponType(
        val category: EWeaponCategory,
        val style: EWeaponStyle,
        val damage: EWeaponDamageAmount,
        val baseDamageType: EDamageType = EDamageType.PHYSICAL
) {
    /**
     * Anti-cavalry weapon of considerably high quality.
     */
    PIKE(category = EWeaponCategory.POLEARM, style = EWeaponStyle.GREAT, damage = EWeaponDamageAmount.MODERATE),
    /**
     * Very high quality weapon without any bonuses.
     */
    LONGSWORD_SHIELD(category = EWeaponCategory.BLADE, style = EWeaponStyle.SHIELD, damage = EWeaponDamageAmount.HIGH),
    /**
     * Strong ranged weapon, effective even against heavy armor. Has to be reloaded after shooting.
     */
    CROSSBOW(category = EWeaponCategory.CROSSBOW, style = EWeaponStyle.RANGED, damage = EWeaponDamageAmount.HIGH)
}