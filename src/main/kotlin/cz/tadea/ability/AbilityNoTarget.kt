package main.kotlin.cz.tadea.ability

import main.kotlin.cz.tadea.tactical.creature.CreatureTactical
import main.kotlin.cz.tadea.tactical.battlefield.Battlefield

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