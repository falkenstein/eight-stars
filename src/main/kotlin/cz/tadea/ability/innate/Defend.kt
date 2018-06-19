package cz.tadea.ability.innate

import main.kotlin.cz.tadea.ability.AbilityNoTarget
import cz.tadea.ability.EAbility
import main.kotlin.cz.tadea.creature.enums.ECreatureFlag
import main.kotlin.cz.tadea.tactical.creature.CreatureTactical
import main.kotlin.cz.tadea.tactical.battlefield.Battlefield

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