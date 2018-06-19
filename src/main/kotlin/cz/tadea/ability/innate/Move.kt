package cz.tadea.ability.innate

import cz.tadea.ability.Ability
import cz.tadea.ability.EAbility
import cz.tadea.creature.enums.ECreatureFlag
import cz.tadea.tactical.creature.CreatureTactical
import cz.tadea.tactical.battlefield.Battlefield
import cz.tadea.tactical.battlefield.BattlefieldZone

/**
 * The only ability in the game that requires targeting, as thus is on the same level as abstract ability classes.
 */
class Move(
        user: CreatureTactical,
        battlefield: Battlefield
) : Ability(user, battlefield) {

    override val associatedEnum = EAbility.MOVE

    override fun canBeUsed(): Boolean {
        return true
    }

    fun getValidTargets(): List<BattlefieldZone> {
        return emptyList()
    }

    fun execute(target: BattlefieldZone) {
        val currentZone = battlefield.getZoneOfCreature(user)
        if (currentZone != null) {
            currentZone.creature = null
        }
        target.creature = user
        target.onCreatureEntered()
    }
}