package cz.tadea.template

import cz.tadea.creature.enums.*
import cz.tadea.item.EWeaponType
import cz.tadea.tactical.EDamageType

/**
 * Used for parsing creature data from JSON.
 */
class CreatureTemplate(
        val name: String,
        val race: ECreatureRace,
        val armor: EArmorType,
        val type: EUnitType,
        val health: EHealthAmount,
        val weapons: Set<EWeaponType>,
        val flags: Set<ECreatureFlag> = emptySet(),
        val resistances: Map<EDamageType, EDamageResistanceType> = emptyMap()
) {
}