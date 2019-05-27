package main.kotlin.cz.tadea.item

import main.kotlin.cz.tadea.creature.enums.EArmorType
import main.kotlin.cz.tadea.creature.enums.EUnitType

/**
 * Every weapon category is effective only up to certain armor weight. Best in this regard are blunt weapons and crossbows, as they never
 * lose effectiveness no matter the type of armor they are faced with. Axes and muskets, on the other hand, are only effective against light
 * armor and no armor.
 */
enum class EWeaponCategory(
        /**
         * Determines the highest armor type against which this weapon category is still effective. All armor above this weight severely
         * diminishes weapon effectiveness.
         */
        val armorEffectiveness: EArmorType
) {
    /**
     * TODO: somehow involve counter-attack mechanics with blades.
     */
    BLADE(armorEffectiveness = EArmorType.MEDIUM),
    /**
     * Groups together all weapoons conjured via magical means.
     */
    MAGIC_WEAPON(armorEffectiveness = EArmorType.HEAVY),
    /**
     * TODO: make axes more aggressive in comparison to other weapons
     */
    AXE(armorEffectiveness = EArmorType.LIGHT),
    /**
     * Strong against Heavy Armor.
     */
    BLUNT(armorEffectiveness = EArmorType.HEAVY),
    /**
     * While they get no bonus against cavalry, polearms prevent being counter-attacked.
     */
    POLEARM(armorEffectiveness = EArmorType.MEDIUM),
    SCYTHE(armorEffectiveness = EArmorType.NONE),
    /**
     * Staves are used for their magical effect rather than for combat.
     */
    STAFF(armorEffectiveness = EArmorType.NONE),
    /**
     * Natural weapons such as claws.
     */
    INNATE(armorEffectiveness = EArmorType.LIGHT),
    UNARMED(armorEffectiveness = EArmorType.NONE),
    BOW(armorEffectiveness = EArmorType.MEDIUM),
    CROSSBOW(armorEffectiveness = EArmorType.HEAVY),
    FIREARM(armorEffectiveness = EArmorType.LIGHT),
    GLAIVE(armorEffectiveness = EArmorType.MEDIUM) // ranged weapon utilised by Art morfs, returning like a boomerang
}