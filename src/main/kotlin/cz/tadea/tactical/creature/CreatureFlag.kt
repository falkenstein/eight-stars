package cz.tadea.tactical.creature

import cz.tadea.creature.enums.ECreatureFlag

class CreatureFlag(
        val flag: ECreatureFlag,
        val temporary: Boolean = false,
        var duration: Int = 0
)