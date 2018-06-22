package main.kotlin.cz.tadea.item

import main.kotlin.cz.tadea.tactical.EDamageType

enum class EWeaponType(
        val category: EWeaponCategory,
        val style: EWeaponStyle,
        val damage: EWeaponDamageAmount,
        val baseDamageType: EDamageType = EDamageType.PHYSICAL
) {
    PIKE(category = EWeaponCategory.POLEARM, style = EWeaponStyle.GREAT, damage = EWeaponDamageAmount.MODERATE),
    /**
     * Very high quality weapon without any bonuses.
     */
    LONGSWORD_SHIELD(category = EWeaponCategory.BLADE, style = EWeaponStyle.SHIELD, damage = EWeaponDamageAmount.HIGH),
    SABER(category = EWeaponCategory.BLADE, style = EWeaponStyle.SINGLE, damage = EWeaponDamageAmount.MODERATE),
    /**
     * Strong ranged weapon, effective even against heavy armor. Has to be reloaded after shooting.
     */
    CROSSBOW(category = EWeaponCategory.CROSSBOW, style = EWeaponStyle.RANGED, damage = EWeaponDamageAmount.HIGH),
    MUSKET(category = EWeaponCategory.FIREARM, style = EWeaponStyle.RANGED, damage = EWeaponDamageAmount.VERY_HIGH),
    PISTOL(category = EWeaponCategory.FIREARM, style = EWeaponStyle.RANGED, damage = EWeaponDamageAmount.MODERATE)
}