package cz.tadea.ability.command

import cz.tadea.ability.EAbility
import cz.tadea.tactical.battlefield.Battlefield
import cz.tadea.tactical.battlefield.BattlefieldZone
import cz.tadea.tactical.creature.CreatureTactical

/**
 * Orders the target creature to defend.
 */
class DefendCommand(
        user: CreatureTactical,
        battlefield: Battlefield
) : Command(user, battlefield) {

    override val associatedEnum: EAbility = EAbility.DEFEND_COMMAND

    override fun execute(target: BattlefieldZone?) {
        val targetCreature = target!!.creature ?: throw IllegalArgumentException("Target zone has no creature in it.")
        /**
         * The creature gets an extra Defend action through this.
         */
        battlefield.useAbility(targetCreature, EAbility.DEFEND, targetZone = null)
    }
}