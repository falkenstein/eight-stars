package main.kotlin.cz.tadea.creature.morf

import org.junit.Assert.*
import org.junit.Test

class MorfClassTest{

    @Test
    fun calculateStatistics() {
        val armorStats = MorfClass.values().map { it.armor }.groupingBy { it }.eachCount()
        println(armorStats)
        val weaponStats = MorfClass.values().map { it.weaponType }.flatten().groupingBy { it }.eachCount()
        println(weaponStats)
    }
}