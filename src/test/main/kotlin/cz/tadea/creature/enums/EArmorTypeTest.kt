package main.kotlin.cz.tadea.creature.enums

import main.kotlin.cz.tadea.creature.enums.EArmorType
import org.junit.Assert.*
import org.junit.Test

class EArmorTypeTest {

    @Test
    fun testIsHeavier() {
        assertTrue(EArmorType.HEAVY.isHeavierThan(EArmorType.LIGHT))
        assertTrue(EArmorType.HEAVY.isHeavierThan(EArmorType.MEDIUM))
        assertTrue(EArmorType.HEAVY.isHeavierThan(EArmorType.NONE))
        assertFalse(EArmorType.HEAVY.isHeavierThan(EArmorType.HEAVY))
        assertFalse(EArmorType.LIGHT.isHeavierThan(EArmorType.HEAVY))
    }
}