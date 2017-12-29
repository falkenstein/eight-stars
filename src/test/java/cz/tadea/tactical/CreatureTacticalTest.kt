package cz.tadea.tactical

import cz.tadea.AbstractTest
import cz.tadea.singleton.TemplateStore
import cz.tadea.tactical.creature.CreatureTactical
import org.junit.Test

class CreatureTacticalTest : AbstractTest() {

    @Test
    fun testSetupFromTemplate() {
        TemplateStore.scanTemplates()
        val creature: CreatureTactical = CreatureTactical(playerA, TemplateStore.getCreature("Pikeman"))
        assert(creature.name == "Pikeman")
    }
}