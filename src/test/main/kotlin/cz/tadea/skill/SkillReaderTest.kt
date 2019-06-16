package main.kotlin.cz.tadea.skill

import org.junit.Assert.*
import org.junit.Test
import java.util.*

class SkillReaderTest {

    val skillReader = SkillReader()

    @Test
    fun test() {
        val descs = skillReader.read()
        val grandMasters = descs.flatMap { it.grandmaster }.groupingBy { it }.eachCount().toList().sortedBy { it.second }.toMap()
        val masters = descs.flatMap { it.master }.groupingBy { it }.eachCount().toList().sortedBy { it.second }.toMap()
        val experts = descs.flatMap { it.expert }.groupingBy { it }.eachCount().toList().sortedBy { it.second }.toMap()
        val countBySkill = mutableMapOf<SkillType, Int>()
        SkillType.values().forEach { countBySkill.put(it, 0) }
        grandMasters.forEach { countBySkill.put(it.key, countBySkill.get(it.key)!! + it.value) }
        masters.forEach { countBySkill.put(it.key, countBySkill.get(it.key)!! + it.value) }
        experts.forEach { countBySkill.put(it.key, countBySkill.get(it.key)!! + it.value) }
        println(countBySkill.toList().sortedBy { it.second }.toMap())
    }
}