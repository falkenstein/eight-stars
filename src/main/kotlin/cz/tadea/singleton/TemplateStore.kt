package main.kotlin.cz.tadea.singleton

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import main.kotlin.cz.tadea.template.CreatureTemplate
import org.apache.commons.io.IOUtils
import java.io.IOException

/**
 * Holds all parsed creature templates.
 */
object TemplateStore {
    val creatureTemplates: MutableSet<CreatureTemplate> = hashSetOf()
    val mapper = ObjectMapper().registerModule(KotlinModule())

    /**
     * Scans all templates. Called at the start of the application.
     */
    fun scanTemplates() {
        creatureTemplates.addAll(mapper.readValue(getFileWithUtil("json/creatures.json")))
    }

    private fun getFileWithUtil(fileName: String): String {
        var result = "";

        val classLoader: ClassLoader = this.javaClass.classLoader;
        try {
            result = IOUtils.toString(classLoader.getResourceAsStream(fileName));
        } catch (e: IOException) {
            e.printStackTrace();
        }
        return result;
    }

    fun getCreature(name: String? = null, id: Int? = null): CreatureTemplate {
        val found = creatureTemplates.find { creature -> creature.name.equals(name, ignoreCase = true) }
        if (found != null) {
            return found
        } else {
            throw IllegalArgumentException("Creature with given name doesn't exist among templates. Name: $name")
        }
    }
}