package main.kotlin.cz.tadea.template

import cz.tadea.ability.EAbility
import cz.tadea.item.EWeaponType
import main.kotlin.cz.tadea.creature.enums.*
import main.kotlin.cz.tadea.tactical.EDamageType

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
        val resistances: Map<EDamageType, EDamageResistanceType> = emptyMap(),
        val abilities: Set<EAbility> = emptySet()
) {
}