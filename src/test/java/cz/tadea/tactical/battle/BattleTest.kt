package cz.tadea.tactical.battle

import cz.tadea.AbstractTest
import cz.tadea.creature.company.Company
import cz.tadea.singleton.TemplateStore
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class BattleTest : AbstractTest() {

    @Before
    fun setUp() {
        TemplateStore.scanTemplates()
    }

    @Test
    fun testBasicBattle() {
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
        val companyB = Company(
                playerB,
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
        val battle: Battle = Battle(players, mapOf(Pair(playerA, companyA), Pair(playerB, companyB)))
    }
}