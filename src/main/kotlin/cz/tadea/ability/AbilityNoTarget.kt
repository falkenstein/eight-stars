package cz.tadea.ability

import cz.tadea.tactical.creature.CreatureTactical
import cz.tadea.tactical.battlefield.Battlefield
import cz.tadea.tactical.battlefield.BattlefieldSide
import cz.tadea.tactical.battlefield.BattlefieldZone

/**
 * Abstract ability class from which all other abilities inherit.
 */
abstract class AbilityNoTarget(
        user: CreatureTactical,
        battlefield: Battlefield
) : Ability(user, battlefield) {

    /**
     * performs the ability.
     */
    abstract fun execute()

}