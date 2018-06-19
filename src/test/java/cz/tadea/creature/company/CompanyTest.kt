package cz.tadea.creature.company

import cz.tadea.AbstractTest
import main.kotlin.cz.tadea.singleton.TemplateStore
import main.kotlin.cz.tadea.tactical.battle.Battle
import main.kotlin.cz.tadea.creature.company.Company
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class CompanyTest : AbstractTest() {

    @Before
    fun setUp() {
        TemplateStore.scanTemplates()
    }

    @Test
    fun testSecondaryConstructor() {
        val companyA = Company(
                playerA,
                listOf(
                        TemplateStore.getCreature("pikeman"),
                        TemplateStore.getCreature("longswordman"),
                        TemplateStore.getCreature("longswordman"),
                        TemplateStore.getCreature("pikeman")
                ),
                listOf(
                        TemplateStore.getCreature("crossbowman"),
                        TemplateStore.getCreature("crossbowman"),
                        TemplateStore.getCreature("crossbowman")
                ),
                TemplateStore.getCreature("captain")
        )

        val companyB = Company(playerB, listOf("pikeman", "longswordman", "longswordman", "pikeman", "captain", "crossbowman", "crossbowman", "crossbowman"))

        val battle = Battle(players, mapOf(Pair(playerA, companyA), Pair(playerB, companyB)))

        for (x in 0..battle.battlefield.size) {
            for (y in 0..1) {
                val creatureA = battle.battlefield.getCreature(playerA, x, y)
                if (creatureA != null) {
                    val creatureB = battle.battlefield.getCreature(playerA, x, y)
                    assertNotNull(creatureB)
                    assertEquals(creatureA.name, creatureB!!.name)
                }
            }
        }
    }
}