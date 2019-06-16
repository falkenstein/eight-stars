package main.kotlin.cz.tadea.skill

enum class SkillType(
        val type: Type
) {
    // Weapon type
    BLADE(Type.WEAPON),
    AXE(Type.WEAPON),
    BLUNT(Type.WEAPON),
    POLEARM(Type.WEAPON),
    BOW(Type.WEAPON),
    CROSSBOW(Type.WEAPON),
    FIREARM(Type.WEAPON),
    GLAIVE(Type.WEAPON),
    UNARMED(Type.WEAPON),
    STAFF(Type.WEAPON),
    // Weapon style
    SINGLE(Type.STYLE),
    DOUBLE(Type.STYLE),
    DUAL(Type.STYLE),
    GREAT(Type.STYLE),
    SHIELD(Type.STYLE),
    // Armor
    UNARMORED(Type.ARMOR),
    LIGHT_ARMOR(Type.ARMOR),
    MEDIUM_ARMOR(Type.ARMOR),
    HEAVY_ARMOR(Type.ARMOR),
    // Combat
    ATHLETICS(Type.COMBAT),
    SMITHING(Type.COMBAT),
    TACTICS(Type.COMBAT),
    ARMSMASTER(Type.COMBAT),
    // Nature
    SURVIVAL(Type.NATURE),
    HERBALISM(Type.NATURE),
    ANIMALS(Type.NATURE),
    NATURAL_MAGIC(Type.NATURE),
    // Arcane
    ALCHEMY(Type.ARCANE),
    LORE(Type.ARCANE),
    SPELLCRAFT(Type.ARCANE),
    ARCANE_MAGIC(Type.ARCANE),
    // Spirit
    SPIRITUALITY(Type.SPIRIT),
    ENCHANTMENT(Type.SPIRIT),
    NECROMANCY(Type.SPIRIT),
    SPIRIT_MAGIC(Type.SPIRIT),
    // Psionic
    TELEKINESIS(Type.PSIONIC),
    TELEPATHY(Type.PSIONIC),
    FOCUS(Type.PSIONIC),
    FLIGHT(Type.PSIONIC),
    // Shadow
    STEALTH(Type.SHADOW),
    THIEVERY(Type.SHADOW),
    POISON(Type.SHADOW),
    SHADOW_MAGIC(Type.SHADOW),
    // Light
    HEALING(Type.LIGHT),
    EVOCATION(Type.LIGHT),
    DEVOTION(Type.LIGHT),
    LIGHT_MAGIC(Type.LIGHT),
    // Art
    ACROBATICS(Type.ART),
    COERCION(Type.ART),
    RUNES(Type.ART),
    ENGINEERING(Type.ART),
    // Generic
}