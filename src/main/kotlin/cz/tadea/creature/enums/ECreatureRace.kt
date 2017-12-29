package cz.tadea.creature.enums

/**
 * Possible creature races. Each falls into one particular type.
 */
enum class ECreatureRace(
        val type: ECreatureType
) {
    HUMAN(ECreatureType.HUMANOID),
    SYLAN(ECreatureType.HUMANOID),
    LACEARAN(ECreatureType.HUMANOID),
    MEERAN(ECreatureType.HUMANOID),
    NIVALE(ECreatureType.HUMANOID)
}