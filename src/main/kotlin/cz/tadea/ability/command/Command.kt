package cz.tadea.ability.command

import cz.tadea.ability.Ability
import cz.tadea.tactical.battlefield.Battlefield
import cz.tadea.tactical.battlefield.BattlefieldZone
import cz.tadea.tactical.creature.CreatureTactical

/**
 * One class for all order-based abilities. Unique in that these abilities are evaluated in the order assignment phase.
 */
abstract class Command(
        user: CreatureTactical,
        battlefield: Battlefield
) : Ability(user, battlefield) {

    override fun getValidTargets(): List<BattlefieldZone> {
        return super.getOwnBattlefieldSide().getAllZones().filter { zone -> zone.creature != null }
    }

    override fun canBeUsed(): Boolean {
        return true
    }
}