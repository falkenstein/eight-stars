package cz.tadea.tactical.creature

import cz.tadea.ability.Ability
import cz.tadea.ability.EAbility
import cz.tadea.creature.enums.ECreatureFlag
import cz.tadea.creature.enums.EDamageResistanceType
import cz.tadea.item.EWeaponType
import cz.tadea.player.Player
import cz.tadea.tactical.EDamageType
import cz.tadea.tactical.battlefield.Battlefield
import cz.tadea.template.CreatureTemplate

/**
 * Represents a creature on the battlefield.
 */
class CreatureTactical(
        val owner: Player,
        val template: CreatureTemplate
) {
    private val flags: MutableList<CreatureFlag> = mutableListOf()
    /**
     * Abilities are initialized when the creature is being inserted into the battlefield.
     */
    private val abilities: MutableList<Ability> = mutableListOf()
    val resistances: MutableMap<EDamageType, EDamageResistanceType> = mutableMapOf()
    val weapons: MutableList<EWeaponType> = mutableListOf()
    var hp: Int = setupHp()
    var name: String = setupName()
    /**
     * Defines if this unit is alive, i.e. in the fight. Works the same way for undead units.
     */
    var alive: Boolean = true

    init {
        // Permanent flags
        template.flags.mapTo(flags) { CreatureFlag(it) }
        weapons.addAll(template.weapons)
        resistances.putAll(template.resistances)
    }

    fun setupHp(): Int {
        return template.health.hp
    }

    fun setupName(): String {
        return template.name
    }

    /**
     * Triggered when the creature is inserted into the battlefield. Initializes abilities.
     */
    fun onInsertedToBattlefield(battlefield: Battlefield) {
        abilities.add(EAbility.ATTACK.getInstance(this, battlefield))
        abilities.add(EAbility.DEFEND.getInstance(this, battlefield))
    }

    /**
     * Triggered on end of every turn.
     */
    fun onEndTurn() {
        // Flags - remove all flags that are outdated.
        for (flag in flags) {
            if (flag.temporary) {
                flag.duration--
                if (flag.duration <= 0) {
                    flags.remove(flag)
                }
            }
        }
    }

    fun hasFlag(flag: ECreatureFlag): Boolean {
        return flags.any { fl -> fl.flag == flag }
    }

    /**
     * Returns ability required by the given enum, if the creature has it. Exception otherwise.
     */
    fun getAbility(ability: EAbility): Ability {
        val found = abilities.find { ab -> ab.associatedEnum == ability }
        if (found == null) {
            throw IllegalArgumentException("Creature $this doesn't have required ability $ability.")
        } else {
            return found
        }
    }

    /**
     * Kills the creature. Pretty much all temporary stats and buffs are removed.
     */
    fun die() {
        alive = false
    }

    override fun toString(): String {
        return name
    }
}