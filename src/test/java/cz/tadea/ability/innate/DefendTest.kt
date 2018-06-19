package cz.tadea.ability.innate

import cz.tadea.AbstractTest
import cz.tadea.ability.EAbility
import main.kotlin.cz.tadea.creature.enums.ECreatureFlag
import main.kotlin.cz.tadea.singleton.TemplateStore
import main.kotlin.cz.tadea.tactical.battlefield.Battlefield
import main.kotlin.cz.tadea.tactical.creature.CreatureTactical
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class DefendTest : AbstractTest() {

    val battlefield: Battlefield = Battlefield(players)

    @Before
    internal fun setUp() {
        TemplateStore.scanTemplates()
    }

    @Test
    fun testDefenderTakesLessDamage() {
        val creatureA1 = CreatureTactical(playerA, TemplateStore.getCreature("pikeman"))
        val creatureA2 = CreatureTactical(playerA, TemplateStore.getCreature("pikeman"))
        val creatureB1 = CreatureTactical(playerB, TemplateStore.getCreature("longswordman"))
        val creatureB2 = CreatureTactical(playerB, TemplateStore.getCreature("longswordman"))
        battlefield.initializeCreature(creatureA1, 1, 0)
        battlefield.initializeCreature(creatureA2, 3, 0)
        battlefield.initializeCreature(creatureB1, 1, 0)
        battlefield.initializeCreature(creatureB2, 3, 0)

        // One of A creatures defends
        battlefield.useAbility(creatureA1, EAbility.DEFEND, null)
        // Check the Defend status is present
        assertTrue(creatureA1.hasFlag(ECreatureFlag.DEFEND))

        val hpBefore1 = creatureA1.hp
        val hpBefore2 = creatureA2.hp
        // Both Bs attack once - we make sure some damage was dealt.
        battlefield.useAbility(creatureB1, EAbility.ATTACK)
        battlefield.useAbility(creatureB2, EAbility.ATTACK)
        assertTrue(hpBefore1 > creatureA1.hp)
        assertTrue(hpBefore2 > creatureA2.hp)
        // Now also check defender took less damage
        assertTrue(creatureA1.hp > creatureA2.hp)
    }
}