package cz.tadea.singleton

import cz.tadea.AbstractTest
import org.junit.Before
import org.junit.Test

class TemplateStoreTest : AbstractTest() {

    @Before
    fun setUp() {
    }

    @Test
    fun testTemplateParsing() {
        TemplateStore.scanTemplates()
        assert(!TemplateStore.creatureTemplates.isEmpty())
        assert(TemplateStore.creatureTemplates.all { creature -> creature.name.isNotBlank() })
        assert(TemplateStore.creatureTemplates.all { creature -> creature.weapons.isNotEmpty() })
    }
}
