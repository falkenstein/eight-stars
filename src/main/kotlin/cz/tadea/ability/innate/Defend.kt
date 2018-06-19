package cz.tadea.ability.innate

import cz.tadea.ability.Ability
import cz.tadea.ability.AbilityNoTarget
import cz.tadea.ability.EAbility
import cz.tadea.creature.enums.ECreatureFlag
import cz.tadea.tactical.creature.CreatureTactical
import cz.tadea.tactical.battlefield.Battlefield
import cz.tadea.tactical.battlefield.BattlefieldZone

/**
 * Basic defensive ability - can't be used by a creature on its own, it must be ordered externally.
 */
class Defend(
        user: CreatureTactical,
        battlefield: Battlefield
) : AbilityNoTarget(user, battlefield) {

    companion object {
        const val DAMAGE_REDUCTION: Int = 20
    }

    override val associatedEnum = EAbility.DEFEND

    override fun canBeUsed(): Boolean {
        return true
    }

    override fun execute() {
        user.addFlag(ECreatureFlag.DEFEND, duration = 1)
    }
}