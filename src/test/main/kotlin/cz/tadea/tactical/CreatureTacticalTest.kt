package main.kotlin.cz.tadea.tactical

import main.kotlin.cz.tadea.AbstractTest
import main.kotlin.cz.tadea.singleton.TemplateStore
import main.kotlin.cz.tadea.tactical.creature.CreatureTactical
import org.junit.Test

class CreatureTacticalTest : AbstractTest() {

    @Test
    fun testSetupFromTemplate() {
        TemplateStore.scanTemplates()
        val creature: CreatureTactical = CreatureTactical(playerA, TemplateStore.getCreature("Pikeman"))
        assert(creature.name == "Pikeman")
    }
}