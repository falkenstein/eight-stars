package cz.tadea.tactical.battlefield

import cz.tadea.AbstractTest
import main.kotlin.cz.tadea.tactical.battlefield.BattlefieldSide
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class BattlefieldSideTest : AbstractTest() {
    val side: BattlefieldSide = BattlefieldSide(playerA)

    @Test
    fun testDistanceCalculation() {
        assert(side.getDistance(side.getZone(0, 1), side.getZone(0, 0)) == 1)
        assert(side.getDistance(side.getZone(4, 1), side.getZone(0, 0)) == 5)
        assert(side.getDistance(side.getZone(4, 1), side.getZone(2, 1)) == 2)
    }

    @Test
    fun testZonesInDistance() {
        val zonesInDistance2 = side.getZonesInDistance(side.getZone(0, 0), 2)
        assert(zonesInDistance2.size == 4)
        val zonesFromMiddle = side.getZonesInDistance(side.getZone(2, 0), 2)
        assertEquals(7, zonesFromMiddle.size)
    }

    @Test
    fun testGetZoneCloserToCenter() {
        assertEquals(1, side.getZoneCloserToCenter(0, 0).x)
        assertEquals(1, side.getZoneCloserToCenter(0, 1).x)
        assertEquals(2, side.getZoneCloserToCenter(1, 1).x)
        assertEquals(3, side.getZoneCloserToCenter(2, 0).x)
        assertEquals(4, side.getZoneCloserToCenter(3, 0).x)
        assertEquals(3, side.getZoneCloserToCenter(4, 0).x)
        assertEquals(4, side.getZoneCloserToCenter(5, 0).x)
        assertEquals(5, side.getZoneCloserToCenter(6, 0).x)
    }

    @Test
    fun getZoneFurtherFromCenter() {
        assertNull(side.getZoneFurtherFromCenter(0, 0))
        assertEquals(0, side.getZoneFurtherFromCenter(1, 0)!!.x)
        assertEquals(1, side.getZoneFurtherFromCenter(2, 0)!!.x)
        assertEquals(2, side.getZoneFurtherFromCenter(3, 0)!!.x)
        assertEquals(5, side.getZoneFurtherFromCenter(4, 0)!!.x)
        assertEquals(6, side.getZoneFurtherFromCenter(5, 0)!!.x)
        assertNull(side.getZoneFurtherFromCenter(6, 0))
    }
}