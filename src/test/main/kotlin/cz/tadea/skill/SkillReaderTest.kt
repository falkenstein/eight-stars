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
        println(grandMasters)
    }
}