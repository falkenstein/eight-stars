package main.kotlin.cz.tadea.ability

import main.kotlin.cz.tadea.tactical.creature.CreatureTactical
import main.kotlin.cz.tadea.tactical.battlefield.Battlefield
import main.kotlin.cz.tadea.tactical.battlefield.BattlefieldSide

/**
 * Abstract ability class from which all other abilities inherit.
 */
abstract class Ability(
        val user: CreatureTactical,
        val battlefield: Battlefield
) {
    abstract val associatedEnum: EAbility
    /**
     * Starts at 0. Not every ability type even takes it into consideration.
     */
    protected var cooldown: Int = 0

    /**
     * Determines whether the ability can currently be used.
     */
    abstract fun canBeUsed(): Boolean

    protected fun getEnemyBattlefieldSide(): BattlefieldSide {
        return battlefield.sides[battlefield.getEnemyPlayer(user.owner)]!!
    }

    protected fun getOwnBattlefieldSide(): BattlefieldSide {
        return battlefield.sides[user.owner]!!
    }

    fun onEndTurn() {
        if (cooldown > 0) {
            cooldown--
        }
    }
}