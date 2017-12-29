package cz.tadea.item

import cz.tadea.creature.enums.EArmorType
import cz.tadea.creature.enums.EUnitType

/**
 * Every weapon category is effective only up to certain armor weight. Best in this regard are blunt weapons and crossbows, as they never
 * lose effectiveness no matter the type of armor they are faced with. Axes and muskets, on the other hand, are only effective against light
 * armor and no armor.
 */
enum class EWeaponCategory(
        /**
         * Unit type against which weapon of this type is super effective.
         */
        val bonus: EUnitType? = null,
        /**
         * Determines the highest armor type against which this weapon category is still effective. All armor above this weight severely
         * diminishes weapon effectiveness.
         */
        val armorEffectiveness: EArmorType
) {
    BLADE(armorEffectiveness = EArmorType.MEDIUM),
    /**
     * Bonus damage versus archers is somewhat mitigated by bad armor penetration.
     */
    AXE(bonus = EUnitType.ARCHER, armorEffectiveness = EArmorType.LIGHT),
    BLUNT(armorEffectiveness = EArmorType.HEAVY),
    POLEARM(bonus = EUnitType.CAVALRY, armorEffectiveness = EArmorType.MEDIUM),
    /**
     * Natural weapons such as claws.
     */
    INNATE(armorEffectiveness = EArmorType.LIGHT),
    BOW(armorEffectiveness = EArmorType.MEDIUM),
    CROSSBOW(armorEffectiveness = EArmorType.HEAVY),
    MUSKET(bonus = EUnitType.INFANTRY, armorEffectiveness = EArmorType.LIGHT)
}