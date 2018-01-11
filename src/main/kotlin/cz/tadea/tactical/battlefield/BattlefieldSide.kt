package cz.tadea.tactical.battlefield

import cz.tadea.ability.Ability
import cz.tadea.ability.EAbility
import cz.tadea.ability.EAbilityPriority
import cz.tadea.player.Player
import cz.tadea.tactical.creature.CreatureTactical

/**
 * Player-owned side of the battlefield.
 */
class BattlefieldSide(
        val owner: Player,
        val size: Int = 6
) {
    val frontRow: Map<Int, BattlefieldZone> = setupRow(0)
    val backRow: Map<Int, BattlefieldZone> = setupRow(1)
    private val selectedAbilities: MutableMap<CreatureTactical, Ability?> = mutableMapOf()

    private fun setupRow(y: Int): Map<Int, BattlefieldZone> {
        val row: MutableMap<Int, BattlefieldZone> = mutableMapOf()
        for (x in 0..size) {
            row.put(x, BattlefieldZone(x, y))
        }
        return row
    }

    /**
     * Return provided position.
     */
    fun getZone(x: Int, y: Int): BattlefieldZone {
        if (x !in 0..size) {
            throw IllegalArgumentException("Invalid X position specified. X: $x, Y: $y")
        } else if (y !in 0..1) {
            throw IllegalArgumentException("Invalid Y position specified. X: $x, Y: $y")
        }
        if (y == 0) {
            return frontRow[x]!!
        } else {
            return backRow[x]!!
        }
    }

    /**
     * Return creature standing in the provided zone. Can be null.
     */
    fun getCreature(x: Int, y: Int): CreatureTactical? {
        val zone: BattlefieldZone = getZone(x, y)
        return zone.creature
    }

    /**
     * Returns a plain set of all zones - e.g. for the purpose of easily searching through all of them.
     */
    fun getAllZones(): Set<BattlefieldZone> {
        val allZones: MutableSet<BattlefieldZone> = mutableSetOf()
        allZones.addAll(frontRow.values)
        allZones.addAll(backRow.values)
        return allZones
    }

    /**
     * Returns distance between two zones.
     */
    fun getDistance(zoneA: BattlefieldZone, zoneB: BattlefieldZone): Int {
        return Math.abs(zoneA.x - zoneB.x) + Math.abs(zoneA.y - zoneB.y)
    }

    /**
     * Returns all zones in given distance from provided zone.
     */
    fun getZonesInDistance(zone: BattlefieldZone, distance: Int): Set<BattlefieldZone> {
        return getAllZones().filter { z -> getDistance(zone, z) <= distance && zone != z }.toSet()
    }

    fun getZoneCloserToCenter(xPosition: Int, yPosition: Int): BattlefieldZone {
        val middleX: Int = size / 2
        if (xPosition < middleX) {
            return getZone(xPosition + 1, yPosition)
        } else if (xPosition > middleX) {
            return getZone(xPosition - 1, yPosition)
        } else { // Equals
            if (middleX % 2 == 0) {
                return getZone(xPosition - 1, yPosition)
            } else {
                return getZone(xPosition + 1, yPosition)
            }
        }
    }

    fun getZoneFurtherFromCenter(xPosition: Int, yPosition: Int): BattlefieldZone? {
        if (xPosition <= 0 || xPosition >= size) {
            return null
        }
        val middleX: Int = size / 2
        return if (xPosition > middleX) {
            getZone(xPosition + 1, yPosition)
        } else {
            getZone(xPosition - 1, yPosition)
        }
    }

    fun onEndTurn() {
        // Reset selected abilities.
        selectedAbilities.keys.forEach { key -> selectedAbilities.put(key, null) }
        getAllZones().forEach { zone -> zone.onEndTurn() }
    }

    /**
     * Selects given ability for the given creature. Checks if the creature exists on the field and if it already doesn't have an ability assigned.
     */
    fun selectAbility(creature: CreatureTactical, ability: Ability) {
        if (selectedAbilities[creature] == null && getAllZones().any { zone -> zone.creature == creature }) {
            selectedAbilities.put(creature, ability)
        }
    }

    /**
     * Called after turn preparation is finished by player - selects default abilities for all present creatures that do not have any ability set yet.
     */
    fun selectDefaultAbilities() {
        getAllZones().forEach { zone ->
            val creature = zone.creature
            if (creature != null && selectedAbilities[creature] == null) {
                // Means we can assign default ability.
                val attack = creature.getAbility(EAbility.ATTACK)
                if (attack.canBeUsed()) {
                    selectAbility(creature, attack)
                } else {
                    selectAbility(creature, creature.getAbility(EAbility.DEFEND))
                }
            }
        }
    }

    fun getCreaturesThatHaveSelectedAbilityWithPriority(priority: EAbilityPriority): List<CreatureTactical> {
        return selectedAbilities.keys.filter { creature -> selectedAbilities[creature]!!.associatedEnum.priority == priority }
    }

    fun getAbilitySelectedByCreature(creature: CreatureTactical): Ability {
        return selectedAbilities[creature] ?: throw IllegalArgumentException("There is no ability for the given creature.")
    }
}