package main.kotlin.cz.tadea.ability.innate

import main.kotlin.cz.tadea.AbstractTest
import main.kotlin.cz.tadea.ability.EAbility
import main.kotlin.cz.tadea.singleton.TemplateStore
import main.kotlin.cz.tadea.tactical.battlefield.Battlefield
import main.kotlin.cz.tadea.tactical.creature.CreatureTactical
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class AttackTest : AbstractTest() {

    val battlefield: Battlefield = Battlefield(players)

    @Before
    fun setUp() {
        TemplateStore.scanTemplates()
    }

    @Test
    fun testValidMeleeTargeting() {
        val creatureA = CreatureTactical(playerA, TemplateStore.getCreature("Hussar"))
        val creatureB = CreatureTactical(playerB, TemplateStore.getCreature("Hussar"))
        battlefield.initializeCreature(creatureA, 1, 0)
        battlefield.initializeCreature(creatureB, 1, 0)
        assertTrue(creatureA.getAbility(EAbility.ATTACK).canBeUsed())
        assertTrue(creatureB.getAbility(EAbility.ATTACK).canBeUsed())
        battlefield.moveCreature(creatureA, 2, 0)
        battlefield.moveCreature(creatureA, 3, 0)
        assertFalse(creatureA.getAbility(EAbility.ATTACK).canBeUsed())
        assertFalse(creatureB.getAbility(EAbility.ATTACK).canBeUsed())
        battlefield.moveCreature(creatureA, 3, 1)
        battlefield.moveCreature(creatureA, 2, 1)
        assertFalse(creatureA.getAbility(EAbility.ATTACK).canBeUsed())
        assertTrue(creatureB.getAbility(EAbility.ATTACK).canBeUsed())
    }

    @Test
    fun testMeleeCorrectTarget() {
        val creatureA = CreatureTactical(playerA, TemplateStore.getCreature("Hussar"))
        val creatureB = CreatureTactical(playerB, TemplateStore.getCreature("Hussar"))
        val creatureC = CreatureTactical(playerB, TemplateStore.getCreature("Hussar"))
        val creatureD = CreatureTactical(playerB, TemplateStore.getCreature("Hussar"))
        battlefield.initializeCreature(creatureA, 1, 0)
        battlefield.initializeCreature(creatureB, 1, 0)
        battlefield.initializeCreature(creatureC, 0, 0)
        battlefield.initializeCreature(creatureD, 2, 0)
        assertTrue(creatureA.getAbility(EAbility.ATTACK).canBeUsed())
        battlefield.moveCreature(creatureB, 1, 1)
        assertTrue(creatureA.getAbility(EAbility.ATTACK).canBeUsed())
        battlefield.moveCreature(creatureD, 3, 0)
        assertTrue(creatureA.getAbility(EAbility.ATTACK).canBeUsed())
    }

    @Test
    fun testValidRangedTargeting() {
        val creatureA = CreatureTactical(playerA, TemplateStore.getCreature("Musketeer"))
        val creatureB = CreatureTactical(playerB, TemplateStore.getCreature("Hussar"))
        battlefield.initializeCreature(creatureA, 1, 0)
        battlefield.initializeCreature(creatureB, 1, 0)
        assertFalse(creatureA.getAbility(EAbility.ATTACK).canBeUsed())
        battlefield.moveCreature(creatureA, 1, 1)
        assertTrue(creatureA.getAbility(EAbility.ATTACK).canBeUsed())
        battlefield.moveCreature(creatureB, 1, 1)
        assertTrue(creatureA.getAbility(EAbility.ATTACK).canBeUsed())
        battlefield.moveCreature(creatureB, 2, 1)
        assertTrue(creatureA.getAbility(EAbility.ATTACK).canBeUsed())
        battlefield.moveCreature(creatureB, 3, 1)
        assertFalse(creatureA.getAbility(EAbility.ATTACK).canBeUsed())
    }

    @Test
    fun testUsage() {
        val creatureA = CreatureTactical(playerA, TemplateStore.getCreature("Hussar"))
        val creatureB = CreatureTactical(playerB, TemplateStore.getCreature("Hussar"))
        battlefield.initializeCreature(creatureA, 1, 0)
        battlefield.initializeCreature(creatureB, 1, 0)
        val hpBefore = creatureB.hp
        battlefield.useAbility(creatureA, EAbility.ATTACK)
        assertTrue(hpBefore > creatureB.hp)
        for (i in 0..30) { // Should be more than enough to kill the target
            if (creatureB.alive) {
                battlefield.useAbility(creatureA, EAbility.ATTACK)
            }
        }
        assertFalse(creatureB.alive)
    }
}