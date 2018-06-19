package main.kotlin.cz.tadea.tactical.creature

import main.kotlin.cz.tadea.ability.Ability
import main.kotlin.cz.tadea.ability.EAbility
import main.kotlin.cz.tadea.creature.enums.EArmorType
import main.kotlin.cz.tadea.creature.enums.ECreatureFlag
import main.kotlin.cz.tadea.creature.enums.EDamageResistanceType
import main.kotlin.cz.tadea.creature.enums.EUnitType
import main.kotlin.cz.tadea.item.EWeaponType
import main.kotlin.cz.tadea.player.Player
import main.kotlin.cz.tadea.tactical.EDamageType
import main.kotlin.cz.tadea.tactical.battlefield.Battlefield
import main.kotlin.cz.tadea.template.CreatureTemplate

/**
 * Represents a creature on the battlefield.
 */
class CreatureTactical(
        val owner: Player,
        private val template: CreatureTemplate
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
        abilities.add(EAbility.MOVE.getInstance(this, battlefield))
    }

    /**
     * Triggered on end of every turn.
     */
    fun onEndTurn() {
        // Flags - remove all flags that are outdated.
        val flagsToRemove = mutableListOf<CreatureFlag>()
        for (flag in flags) {
            if (flag.temporary) {
                flag.duration--
                if (flag.duration <= 0) {
                    flagsToRemove.add(flag)
                }
            }
        }
        flags.removeAll(flagsToRemove)
    }

    fun hasFlag(flag: ECreatureFlag): Boolean {
        return flags.any { fl -> fl.flag == flag }
    }

    fun addFlag(flag: ECreatureFlag, duration: Int = 0, temporary: Boolean = true) {
        val existingFlag = flags.find { fl -> fl.flag == flag }
        if (existingFlag != null && existingFlag.temporary && temporary) {
            existingFlag.duration = maxOf(duration, existingFlag.duration)
        } else if (existingFlag == null) {
            flags.add(CreatureFlag(flag, duration = duration, temporary = temporary))
        }
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

    /**
     * Calculates armor of the creature. TODO: Take buffs and debuffs into account.
     */
    fun getArmor(): EArmorType {
        return template.armor
    }

    fun getType(): EUnitType {
        return template.type
    }
}