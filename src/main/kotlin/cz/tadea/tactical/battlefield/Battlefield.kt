package cz.tadea.tactical.battlefield

import cz.tadea.ability.EAbility
import cz.tadea.creature.enums.ECreatureFlag
import cz.tadea.creature.enums.EDamageResistanceType
import cz.tadea.creature.enums.EUnitType
import cz.tadea.exception.CannotUseAbilityException
import cz.tadea.player.Player
import cz.tadea.tactical.EDamageType
import cz.tadea.tactical.creature.CreatureTactical

/**
 * Represents the battlefield on which the individual tactical battle takes place.
 */
class Battlefield(
        val players: Set<Player>
) {
    val sides: Map<Player, BattlefieldSide> = players.associateBy(keySelector = { player -> player }, valueTransform = { player -> BattlefieldSide(player) })

    /**
     * Return provided position.
     */
    fun getZone(player: Player, x: Int, y: Int): BattlefieldZone {
        if (!players.contains(player)) {
            throw IllegalArgumentException("Provided player doesn't exist on this battlefield. Player: $player")
        }
        return sides[player]!!.getZone(x, y)
    }

    /**
     * Return creature standing in the provided zone. Can be null.
     */
    fun getCreature(player: Player, x: Int, y: Int): CreatureTactical? {
        if (!players.contains(player)) {
            throw IllegalArgumentException("Provided player doesn't exist on this battlefield. Player: $player")
        }
        return sides[player]!!.getCreature(x, y)
    }

    /**
     * Returns position of the given creature.
     */
    fun getZoneOfCreature(creature: CreatureTactical): BattlefieldZone? {
        val allZones = sides[creature.owner]!!.getAllZones()
        for (zone in allZones) {
            if (zone.creature != null && zone.creature == creature) {
                return zone
            }
        }
        return null
    }

    fun isCreatureInFrontZone(creature: CreatureTactical): Boolean {
        val zone = getZoneOfCreature(creature)
        if (zone != null) {
            return zone.isFront()
        } else {
            throw IllegalArgumentException("Given creature is not on battlefield yet.")
        }
    }

    /**
     * Move given creature into the given position. The creature must already be located on battlefield.
     */
    fun moveCreature(creature: CreatureTactical, x: Int, y: Int) {
        val newPosition = getZone(creature.owner, x, y)
        moveCreature(creature, newPosition)
    }

    /**
     * Moves the creature. Only works if it already is on battlefield.
     */
    fun moveCreature(creature: CreatureTactical, newPosition: BattlefieldZone) {
        val oldPosition = getZoneOfCreature(creature) ?: throw IllegalArgumentException("The given creature is not on battlefield yet.")
        if (getZonesForMove(creature).contains(newPosition)) {
            oldPosition.creature = null
            newPosition.creature = creature
            newPosition.onCreatureEntered()
        } else {
            throw IllegalArgumentException("Requested move action is not possible.")
        }
    }

    /**
     * Inserts the creature into the battlefield and initializes it.
     */
    fun initializeCreature(creature: CreatureTactical, x: Int, y: Int) {
        val newPosition = getZone(creature.owner, x, y)
        initializeCreature(creature, newPosition)
    }

    /**
     * Inserts the creature into the battlefield and initializes it.
     */
    fun initializeCreature(creature: CreatureTactical, newPosition: BattlefieldZone) {
        if (getZonesForMove(creature).contains(newPosition)) {
            newPosition.creature = creature
            creature.onInsertedToBattlefield(this)
            newPosition.onCreatureEntered()
        } else {
            throw IllegalArgumentException("Creature can't be placed into the given position: $newPosition")
        }
    }

    /**
     * Prepares set of zones to which the given creature can move.
     */
    fun getZonesForMove(creature: CreatureTactical): Set<BattlefieldZone> {
        val currentPosition = getZoneOfCreature(creature)
        if (currentPosition == null) {
            // The creature can be placed into any free zone in this case.
            return sides[creature.owner]!!.getAllZones().filter { zone -> zone.canBeMovedInto() }.toSet()
        } else {
            val distance: Int = if (creature.getType() == EUnitType.CAVALRY || creature.hasFlag(ECreatureFlag.RUNNER)) 4 else 1
            return sides[creature.owner]!!.getZonesInDistance(currentPosition, distance).filter { zone -> zone.canBeMovedInto() }.toSet()
        }
    }

    fun useAbility(creature: CreatureTactical, abilityEnum: EAbility, target: CreatureTactical) {
        useAbility(creature, abilityEnum, getZoneOfCreature(target))
    }

    fun useAbility(creature: CreatureTactical, abilityEnum: EAbility, target: BattlefieldZone?) {
        val ability = creature.getAbility(abilityEnum)
        if (ability.canBeUsed()) {
            if (abilityEnum.requiresTarget) {
                if (ability.getValidTargets().contains(target)) {
                    ability.execute(target)
                } else {
                    throw CannotUseAbilityException("Provided target $target for ability $abilityEnum is not valid.")
                }
            } else {
                ability.execute(null)
            }
        } else {
            throw CannotUseAbilityException("Ability $abilityEnum can't be used now.")
        }
    }

    fun getEnemyPlayer(player: Player): Player {
        if (!players.contains(player)) {
            throw IllegalArgumentException("Invalid player provided.")
        }
        return players.first { pl -> pl.id != player.id }
    }

    /**
     * The string is used for reporting.
     */
    fun damageCreature(damaged: CreatureTactical, damage: List<Triple<EDamageType, Int, String>>, originator: CreatureTactical) {
        for (dmg in damage) {
            var totalDamage: Int = dmg.second
            if (damaged.resistances.containsKey(dmg.first)) {
                val resistance: EDamageResistanceType = damaged.resistances[dmg.first]!!
                totalDamage -= resistance.flatReduction
                totalDamage *= (100 - resistance.percentageReduction)
            }
            damaged.hp -= totalDamage
        }
        if (damaged.hp <= 0) {
            killCreature(damaged, originator)
        }
    }

    /**
     * A creature dies - is removed from the game.
     */
    fun killCreature(killed: CreatureTactical, originator: CreatureTactical) {
        val zone: BattlefieldZone = getZoneOfCreature(killed)!!
        killed.die()
        zone.creature = null
        println("$killed was killed by $originator.")
    }
}