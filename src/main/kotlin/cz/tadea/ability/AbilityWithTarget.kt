package cz.tadea.ability

import cz.tadea.tactical.creature.CreatureTactical
import cz.tadea.tactical.battlefield.Battlefield
import cz.tadea.tactical.battlefield.BattlefieldSide
import cz.tadea.tactical.battlefield.BattlefieldZone

/**
 * Most abilities automatically acquire exactly one target.
 */
abstract class AbilityWithTarget(
        user: CreatureTactical,
        battlefield: Battlefield
) : Ability(user, battlefield) {

    /**
     * Returns possible valid targets from which the player can select.
     */
    abstract fun getValidTarget(): BattlefieldZone?

    override fun canBeUsed(): Boolean {
        return getValidTarget() != null
    }

    /**
     * performs the ability.
     */
    abstract fun execute()
}