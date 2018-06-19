package main.kotlin.cz.tadea.tactical.battle

import main.kotlin.cz.tadea.AbstractTest
import main.kotlin.cz.tadea.creature.company.Company
import main.kotlin.cz.tadea.singleton.TemplateStore
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
                        TemplateStore.getCreature("longswordman"),
                        TemplateStore.getCreature("longswordman"),
                        TemplateStore.getCreature("longswordman"),
                        TemplateStore.getCreature("longswordman")
                ),
                listOf(),
                TemplateStore.getCreature("captain")
        )
        val companyB = Company(
                playerB,
                listOf(
                        TemplateStore.getCreature("pikeman"),
                        TemplateStore.getCreature("pikeman"),
                        TemplateStore.getCreature("pikeman"),
                        TemplateStore.getCreature("pikeman")
                ),
                listOf(),
                TemplateStore.getCreature("captain")
        )
        val battle = Battle(players, mapOf(Pair(playerA, companyA), Pair(playerB, companyB)))
        for (i in 0..30) { // The battle must end in reasonable amount of turns.
            battle.evaluateTurn()
            val winners = battle.getWinner()
            if (winners.isNotEmpty()) {
                return
            }
        }
        fail()
    }
}