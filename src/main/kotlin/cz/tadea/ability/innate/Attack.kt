package main.kotlin.cz.tadea.ability.innate

import main.kotlin.cz.tadea.ability.AbilityWithTarget
import main.kotlin.cz.tadea.ability.EAbility
import main.kotlin.cz.tadea.creature.enums.ECreatureFlag
import main.kotlin.cz.tadea.item.EWeaponDamageAmount
import main.kotlin.cz.tadea.item.EWeaponStyle
import main.kotlin.cz.tadea.item.EWeaponType
import main.kotlin.cz.tadea.tactical.EDamageType
import main.kotlin.cz.tadea.tactical.creature.CreatureTactical
import main.kotlin.cz.tadea.tactical.battlefield.Battlefield
import main.kotlin.cz.tadea.tactical.battlefield.BattlefieldZone

/**
 * The default attack with equipped weapon.
 */
class Attack(
        user: CreatureTactical,
        battlefield: Battlefield
) : AbilityWithTarget(user, battlefield) {
    override val associatedEnum = EAbility.ATTACK

    override fun canBeUsed(): Boolean {
        val currentZone = battlefield.getZoneOfCreature(user)!!
        if (currentZone.isFront() && user.weapons.none { weapon -> weapon.style != EWeaponStyle.RANGED }) {
            return false
        } else if (!currentZone.isFront() && user.weapons.none { weapon -> weapon.style == EWeaponStyle.RANGED }) {
            return false
        } else {
            val validTarget = getValidTarget()
            return validTarget != null
        }
    }

    override fun getValidTarget(): BattlefieldZone? {
        val currentZone = battlefield.getZoneOfCreature(user)!!
        if (currentZone.isFront()) {
            // Now check legal weapon.
            if (user.weapons.any { weapon -> weapon.style != EWeaponStyle.RANGED }) {
                return getStandardMeleeTargeting(currentZone)
            }
        } else {
            // Now check legal weapon.
            if (user.weapons.any { weapon -> weapon.style == EWeaponStyle.RANGED }) {
                return getStandardRangedTargeting(currentZone)
            }
        }
        return null
    }

    /**
     * Standard melee targeting - taken in order:
     * - enemy directly opposite to you
     * - enemy on the side that is closer to the center
     * - enemy on the side away from the center
     * - enemy on the back row opposite to you
     * - enemy on back row closer to center
     * - enemy on back row away from the center
     */
    protected fun getStandardMeleeTargeting(currentZone: BattlefieldZone): BattlefieldZone? {
        val xPosition = currentZone.x
        val enemySide = getEnemyBattlefieldSide()
        if (canStrikeZone(enemySide.getZone(xPosition, 0))) { // direct opposite position
            return enemySide.getZone(xPosition, 0)
        } else if (canStrikeZone(enemySide.getZoneCloserToCenter(xPosition, 0))) {
            return enemySide.getZoneCloserToCenter(xPosition, 0)
        } else if (enemySide.getZoneFurtherFromCenter(xPosition, 0) != null && canStrikeZone(enemySide.getZoneFurtherFromCenter(xPosition, 0)!!)) {
            return enemySide.getZoneFurtherFromCenter(xPosition, 0)
        } else if (canStrikeZone(enemySide.getZone(xPosition, 1))) {
            return enemySide.getZone(xPosition, 1)
        } else if (canStrikeZone(enemySide.getZoneCloserToCenter(xPosition, 1))) {
            return enemySide.getZoneCloserToCenter(xPosition, 1)
        } else if (enemySide.getZoneFurtherFromCenter(xPosition, 1) != null && canStrikeZone(enemySide.getZoneFurtherFromCenter(xPosition, 1)!!)) {
            return enemySide.getZoneFurtherFromCenter(xPosition, 1)
        } else {
            return null
        }
    }

    /**
     * Standard ranged targeting - taken in order:
     * - front directly opposite
     * - back opposite (different from melee)
     * - front to center
     * - back to center
     * - front away from center
     * - back away from center
     */
    protected fun getStandardRangedTargeting(currentZone: BattlefieldZone): BattlefieldZone? {
        val xPosition = currentZone.x
        val enemySide = getEnemyBattlefieldSide()
        if (canStrikeZone(enemySide.getZone(xPosition, 0))) { // direct opposite position
            return enemySide.getZone(xPosition, 0)
        } else if (canStrikeZone(enemySide.getZone(xPosition, 1))) {
            return enemySide.getZone(xPosition, 1)
        } else if (canStrikeZone(enemySide.getZoneCloserToCenter(xPosition, 0))) {
            return enemySide.getZoneCloserToCenter(xPosition, 0)
        } else if (canStrikeZone(enemySide.getZoneCloserToCenter(xPosition, 1))) {
            return enemySide.getZoneCloserToCenter(xPosition, 1)
        } else if (enemySide.getZoneFurtherFromCenter(xPosition, 0) != null && canStrikeZone(enemySide.getZoneFurtherFromCenter(xPosition, 0)!!)) {
            return enemySide.getZoneFurtherFromCenter(xPosition, 0)
        } else if (enemySide.getZoneFurtherFromCenter(xPosition, 1) != null && canStrikeZone(enemySide.getZoneFurtherFromCenter(xPosition, 1)!!)) {
            return enemySide.getZoneFurtherFromCenter(xPosition, 1)
        } else {
            return null
        }
    }

    /**
     * TODO: Add modifiers such as ethereal etc.
     */
    private fun canStrikeZone(zone: BattlefieldZone): Boolean {
        return zone.creature != null
    }

    override fun execute() {
        val targetCreature = getValidTarget()?.creature ?: throw IllegalArgumentException("Attack requires a creature to stand on target zone.")
        val allDamage: MutableList<Triple<EDamageType, Int, String>> = mutableListOf()

        // Creature's weapon
        val usedWeapon: EWeaponType
        usedWeapon = if (battlefield.getZoneOfCreature(user)!!.isFront()) {
            user.weapons.first { weapon -> weapon.style != EWeaponStyle.RANGED }
        } else {
            user.weapons.first { weapon -> weapon.style == EWeaponStyle.RANGED }
        }
        allDamage.add(Triple(usedWeapon.baseDamageType, calculateWeaponDamage(user, targetCreature, usedWeapon), "Base weapon damage."))

        battlefield.damageCreature(targetCreature, allDamage, user)
    }

    /**
     * Calculates the damage inflicted by given weapon.
     */
    protected fun calculateWeaponDamage(user: CreatureTactical, target: CreatureTactical, weapon: EWeaponType): Int {
        var damage = weapon.damage.damage // We begin with weapon's base damage.
        if (target.getArmor().isHeavierThan(weapon.category.armorEffectiveness)) { // Damage halved for ineffective weapon vs armor.
            damage /= 2
        }
        damage -= target.getArmor().damageReduction

        if (target.hasFlag(ECreatureFlag.DEFEND)) {
            damage -= Defend.DAMAGE_REDUCTION
        }

        if (damage < EWeaponDamageAmount.MINIMUM_DAMAGE) {
            damage = EWeaponDamageAmount.MINIMUM_DAMAGE
        }

        return damage
    }
}