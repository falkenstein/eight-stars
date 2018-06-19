package main.kotlin.cz.tadea.ability.innate

import main.kotlin.cz.tadea.AbstractTest
import main.kotlin.cz.tadea.ability.EAbility
import main.kotlin.cz.tadea.singleton.TemplateStore
import main.kotlin.cz.tadea.tactical.battlefield.Battlefield
import main.kotlin.cz.tadea.tactical.creature.CreatureTactical
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MoveTest : AbstractTest() {

    val battlefield: Battlefield = Battlefield(players)

    @Before
    fun setUp() {
        TemplateStore.scanTemplates()
    }

    @Test
    fun testCorrectMove() {
        val creatureA = CreatureTactical(playerA, TemplateStore.getCreature("pikeman"))
        battlefield.initializeCreature(creatureA, 0, 0)
        battlefield.useAbility(creatureA, EAbility.MOVE, battlefield.getZone(playerA, 1, 1))
        assertTrue(battlefield.getZone(playerA, 0, 0).creature == null)
        assertTrue(battlefield.getZone(playerA, 1, 1).creature != null)
    }
}