package main.kotlin.cz.tadea.skill

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import main.kotlin.cz.tadea.creature.morf.ClassDescriptionDto

class SkillReader {

    private val mapper = ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

    fun read(): List<ClassDescriptionDto> {
        val text = this::class.java.classLoader.getResource("json/skills.json").readText()
        return mapper.readValue(text)
    }
}