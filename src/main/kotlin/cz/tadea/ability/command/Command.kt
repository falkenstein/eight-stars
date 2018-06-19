package cz.tadea.ability.command

import cz.tadea.ability.Ability
import cz.tadea.ability.AbilityNoTarget
import cz.tadea.tactical.battlefield.Battlefield
import cz.tadea.tactical.battlefield.BattlefieldZone
import cz.tadea.tactical.creature.CreatureTactical

/**
 * One class for all order-based abilities. Unique in that these abilities are evaluated in the order assignment phase.
 */
abstract class Command(
        user: CreatureTactical,
        battlefield: Battlefield
) : AbilityNoTarget(user, battlefield) {

}