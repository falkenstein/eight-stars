package main.kotlin.cz.tadea.ability.command

import main.kotlin.cz.tadea.ability.AbilityNoTarget
import main.kotlin.cz.tadea.tactical.battlefield.Battlefield
import main.kotlin.cz.tadea.tactical.creature.CreatureTactical

/**
 * One class for all order-based abilities. Unique in that these abilities are evaluated in the order assignment phase.
 */
abstract class Command(
        user: CreatureTactical,
        battlefield: Battlefield
) : AbilityNoTarget(user, battlefield) {

}