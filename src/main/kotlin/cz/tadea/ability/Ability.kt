package cz.tadea.ability

import cz.tadea.tactical.creature.CreatureTactical
import cz.tadea.tactical.battlefield.Battlefield
import cz.tadea.tactical.battlefield.BattlefieldSide
import cz.tadea.tactical.battlefield.BattlefieldZone

/**
 * Abstract ability class from which all other abilities inherit.
 */
abstract class Ability(
        val user: CreatureTactical,
        val battlefield: Battlefield
) {
    abstract val associatedEnum: EAbility

    /**
     * Determines whether the ability can currently be used.
     */
    abstract fun canBeUsed(): Boolean

    /**
     * Returns possible valid targets from which the player can select.
     */
    abstract fun getValidTargets(): List<BattlefieldZone>

    /**
     * performs the ability.
     */
    abstract fun execute(target: BattlefieldZone?)

    protected fun getEnemyBattlefieldSide(): BattlefieldSide {
        return battlefield.sides[battlefield.getEnemyPlayer(user.owner)]!!
    }

    protected fun getOwnBattlefieldSide(): BattlefieldSide {
        return battlefield.sides[user.owner]!!
    }
}