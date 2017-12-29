package cz.tadea.ability

import cz.tadea.ability.innate.Attack
import cz.tadea.ability.innate.Defend
import cz.tadea.tactical.creature.CreatureTactical
import cz.tadea.tactical.battlefield.Battlefield

/**
 * Enum that lists all abilities and permits getting new ability instances.
 */
enum class EAbility(
        val cooldown: Int,
        val selectable: Boolean = true,
        val requiresTarget: Boolean = true
) {
    /**
     * Stands for all kinds of attack. Mostly executed after being automatically ordered.
     */
    ATTACK(
            cooldown = 0,
            selectable = false
    ) {
        override fun getInstance(user: CreatureTactical, battlefield: Battlefield): Ability {
            return Attack(user, battlefield)
        }
    },
    /**
     * Basic defense - avoids attacking and reduces incoming attack damage. Only executed after being given a special order.
     */
    DEFEND(
            cooldown = 0,
            selectable = false,
            requiresTarget = false
    ) {
        override fun getInstance(user: CreatureTactical, battlefield: Battlefield): Ability {
            return Defend(user, battlefield)
        }
    };

    abstract fun getInstance(user: CreatureTactical, battlefield: Battlefield): Ability
}