package cz.tadea.creature.enums

enum class EArmorType(
        val damageReduction: Int
) {
    NONE(0),
    LIGHT(3),
    MEDIUM(5),
    HEAVY(7);

    fun isHeavierThan(type: EArmorType): Boolean {
        return this.ordinal > type.ordinal
    }
}