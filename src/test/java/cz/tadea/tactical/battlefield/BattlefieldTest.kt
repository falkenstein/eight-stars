package cz.tadea.tactical.battlefield

import cz.tadea.AbstractTest
import cz.tadea.singleton.TemplateStore
import cz.tadea.tactical.creature.CreatureTactical
import org.junit.Assert.fail
import org.junit.Test

class BattlefieldTest : AbstractTest() {
    val battlefield = Battlefield(players)
    val testPlayer = players.iterator().next()
    val size = battlefield.sides[testPlayer]!!.size

    @Test
    fun testZoneSetup() {
        for (y in 0..1) {
            for (x in 0..size) {
                assert(battlefield.getZone(testPlayer, x, y).creature == null)
            }
        }
        try {
            battlefield.getZone(testPlayer, 2, 2)
            fail("Exception should have been thrown for invalid zone.")
        } catch (e: IllegalArgumentException) {
            // OK
        }
        try {
            battlefield.getZone(testPlayer, size + 1, 0)
            fail("Exception should have been thrown for invalid zone.")
        } catch (e: IllegalArgumentException) {
            // OK
        }
    }

    @Test
    fun testInsertCreature() {
        TemplateStore.scanTemplates()
        val creature = CreatureTactical(testPlayer, TemplateStore.getCreature("Pikeman"))
        battlefield.initializeCreature(creature, 0, 0)
        try {
            battlefield.moveCreature(creature, 7, 0)
            fail("Exception should have come up because of impossible zone.")
        } catch (e: IllegalArgumentException) {
            // OK
        }
    }
}
