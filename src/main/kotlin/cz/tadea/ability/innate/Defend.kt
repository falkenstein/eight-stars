package cz.tadea.ability.innate

import cz.tadea.ability.Ability
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
) : Ability(user, battlefield) {
    override val associatedEnum = EAbility.DEFEND

    override fun canBeUsed(): Boolean {
        return user.hasFlag(ECreatureFlag.CAN_DEFEND)
    }

    override fun getValidTargets(): List<BattlefieldZone> {
        return emptyList()
    }

    override fun execute(target: BattlefieldZone?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}