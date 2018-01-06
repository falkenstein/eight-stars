package cz.tadea.creature.enums

enum class EArmorType {
    NONE,
    LIGHT,
    MEDIUM,
    HEAVY;

    fun isHeavierThan(type: EArmorType): Boolean {
        return this.ordinal > type.ordinal
    }
}