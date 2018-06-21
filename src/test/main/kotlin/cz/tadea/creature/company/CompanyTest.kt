package main.kotlin.cz.tadea.creature.company

import main.kotlin.cz.tadea.AbstractTest
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
                        TemplateStore.getCreature("Hussar"),
                        TemplateStore.getCreature("longswordman"),
                        TemplateStore.getCreature("longswordman"),
                        TemplateStore.getCreature("Hussar")
                ),
                listOf(),
                TemplateStore.getCreature("captain")
        )

        val companyB = Company(playerB, listOf("Hussar", "longswordman", "longswordman", "Hussar", "captain"))

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