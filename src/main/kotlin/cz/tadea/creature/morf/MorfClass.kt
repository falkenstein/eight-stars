package main.kotlin.cz.tadea.creature.morf

import main.kotlin.cz.tadea.creature.enums.EArmorType
import main.kotlin.cz.tadea.item.EWeaponCategory
import main.kotlin.cz.tadea.item.EWeaponStyle

enum class MorfClass(
        val aspectA: EAspect,
        val aspectB: EAspect,
        val armor: EArmorType,
        val weaponStyle: Set<EWeaponStyle>,
        val weaponType: Set<EWeaponCategory>,
        val theming: String = ""
) {
    BLADEMASTER(
            EAspect.COMBAT, EAspect.COMBAT, EArmorType.MEDIUM,
            setOf(EWeaponStyle.DOUBLE, EWeaponStyle.DUAL, EWeaponStyle.SINGLE),
            setOf(EWeaponCategory.BLADE, EWeaponCategory.FIREARM)
    ),
    DRUID(
            EAspect.NATURE, EAspect.NATURE, EArmorType.LIGHT,
            setOf(EWeaponStyle.DOUBLE),
            setOf(EWeaponCategory.STAFF)
    ),
    LOREKEEPER(
            EAspect.ARCANE, EAspect.ARCANE, EArmorType.NONE,
            setOf(EWeaponStyle.SINGLE),
            setOf(EWeaponCategory.MAGIC_WEAPON)
    ),
    BISHOP(
            EAspect.SPIRIT, EAspect.SPIRIT, EArmorType.MEDIUM,
            setOf(EWeaponStyle.GREAT),
            setOf(EWeaponCategory.POLEARM)
    ),
    MESMER(
            EAspect.PSIONIC, EAspect.PSIONIC, EArmorType.NONE,
            setOf(EWeaponStyle.SINGLE),
            setOf(EWeaponCategory.MAGIC_WEAPON)
    ),
    STALKER(
            EAspect.SHADOW, EAspect.SHADOW, EArmorType.LIGHT,
            setOf(EWeaponStyle.DUAL),
            setOf(EWeaponCategory.BLADE)
    ),
    SEER(
            EAspect.LIGHT, EAspect.LIGHT, EArmorType.LIGHT,
            setOf(EWeaponStyle.SHIELD),
            setOf(EWeaponCategory.BLADE)
    ),
    ROGUE(
            EAspect.ART, EAspect.ART, EArmorType.LIGHT,
            setOf(EWeaponStyle.SINGLE, EWeaponStyle.RANGED),
            setOf(EWeaponCategory.BLADE, EWeaponCategory.GLAIVE)
    ),
    RANGER(
            EAspect.COMBAT, EAspect.NATURE, EArmorType.LIGHT,
            setOf(EWeaponStyle.DUAL, EWeaponStyle.RANGED),
            setOf(EWeaponCategory.AXE, EWeaponCategory.BOW)
    ),
    TEMPEST(
            EAspect.COMBAT, EAspect.ARCANE, EArmorType.NONE,
            setOf(EWeaponStyle.DUAL, EWeaponStyle.RANGED),
            setOf(EWeaponCategory.MAGIC_WEAPON, EWeaponCategory.FIREARM)
    ),
    SEEKER(
            EAspect.COMBAT, EAspect.SPIRIT, EArmorType.HEAVY,
            setOf(EWeaponStyle.SHIELD, EWeaponStyle.GREAT),
            setOf(EWeaponCategory.BLUNT)
    ),
    GUARDIAN(
            EAspect.COMBAT, EAspect.PSIONIC, EArmorType.MEDIUM,
            setOf(EWeaponStyle.GREAT, EWeaponStyle.DOUBLE),
            setOf(EWeaponCategory.BLADE)
    ),
    INQUISITOR(
            EAspect.COMBAT, EAspect.SHADOW, EArmorType.HEAVY,
            setOf(EWeaponStyle.GREAT, EWeaponStyle.RANGED),
            setOf(EWeaponCategory.POLEARM, EWeaponCategory.FIREARM)
    ),
    VINDICATOR(
            EAspect.COMBAT, EAspect.LIGHT, EArmorType.MEDIUM,
            setOf(EWeaponStyle.SINGLE, EWeaponStyle.DOUBLE),
            setOf(EWeaponCategory.MAGIC_WEAPON, EWeaponCategory.BLADE)
    ),
    MYRMIDON(
            EAspect.COMBAT, EAspect.ART, EArmorType.MEDIUM,
            setOf(EWeaponStyle.DOUBLE, EWeaponStyle.RANGED),
            setOf(EWeaponCategory.BLADE, EWeaponCategory.GLAIVE)
    ),
    WARDEN(
            EAspect.NATURE, EAspect.ARCANE, EArmorType.LIGHT,
            setOf(EWeaponStyle.DOUBLE),
            setOf(EWeaponCategory.BLADE)
    ),
    MYSTIC(
            EAspect.NATURE, EAspect.SPIRIT, EArmorType.NONE,
            setOf(EWeaponStyle.DUAL),
            setOf(EWeaponCategory.BLADE)
    ),
    KESTREL(
            EAspect.NATURE, EAspect.PSIONIC, EArmorType.LIGHT,
            setOf(EWeaponStyle.RANGED, EWeaponStyle.SINGLE),
            setOf(EWeaponCategory.BOW, EWeaponCategory.AXE)
    ),
    AVENGER(
            EAspect.NATURE, EAspect.SHADOW, EArmorType.LIGHT,
            setOf(EWeaponStyle.DUAL),
            setOf(EWeaponCategory.BLADE)
    ),
    CELEBRANT(
            EAspect.NATURE, EAspect.LIGHT, EArmorType.MEDIUM,
            setOf(EWeaponStyle.DOUBLE),
            setOf(EWeaponCategory.STAFF)
    ),
    STRIDER(
            EAspect.NATURE, EAspect.ART, EArmorType.MEDIUM,
            setOf(EWeaponStyle.RANGED, EWeaponStyle.SINGLE),
            setOf(EWeaponCategory.BOW, EWeaponCategory.AXE)
    ),
    EIDOLON(
            EAspect.ARCANE, EAspect.SPIRIT, EArmorType.NONE,
            setOf(EWeaponStyle.UNARMED),
            setOf(EWeaponCategory.UNARMED)
    ),
    RIDDLER(
            EAspect.ARCANE, EAspect.PSIONIC, EArmorType.NONE,
            setOf(EWeaponStyle.RANGED, EWeaponStyle.SINGLE),
            setOf(EWeaponCategory.CROSSBOW, EWeaponCategory.BLADE)
    ),
    SHIFTER(
            EAspect.ARCANE, EAspect.SHADOW, EArmorType.NONE,
            setOf(EWeaponStyle.GREAT),
            setOf(EWeaponCategory.MAGIC_WEAPON) // magically bound spear
    ),
    ARBITER(
            EAspect.ARCANE, EAspect.LIGHT, EArmorType.NONE,
            setOf(EWeaponStyle.DOUBLE),
            setOf(EWeaponCategory.STAFF)
    ),
    SCRIVENER(
            EAspect.ARCANE, EAspect.ART, EArmorType.NONE,
            setOf(EWeaponStyle.RANGED, EWeaponStyle.SINGLE),
            setOf(EWeaponCategory.GLAIVE, EWeaponCategory.BLADE)
    ),
    REDEEMER(
            EAspect.SPIRIT, EAspect.PSIONIC, EArmorType.NONE,
            setOf(EWeaponStyle.DOUBLE),
            setOf(EWeaponCategory.BLADE)
    ),
    WRAITH(
            EAspect.SPIRIT, EAspect.SHADOW, EArmorType.LIGHT,
            setOf(EWeaponStyle.GREAT),
            setOf(EWeaponCategory.SCYTHE)
    ),
    DIVINER(
            EAspect.SPIRIT, EAspect.LIGHT, EArmorType.LIGHT,
            setOf(EWeaponStyle.RANGED, EWeaponStyle.SINGLE),
            setOf(EWeaponCategory.MAGIC_WEAPON, EWeaponCategory.BLADE) // conjured bow
    ),
    ARCHON(
            EAspect.SPIRIT, EAspect.ART, EArmorType.LIGHT,
            setOf(EWeaponStyle.DOUBLE, EWeaponStyle.SINGLE),
            setOf(EWeaponCategory.AXE)
    ),
    SENTINEL(
            EAspect.PSIONIC, EAspect.SHADOW, EArmorType.LIGHT,
            setOf(EWeaponStyle.DUAL),
            setOf(EWeaponCategory.BLADE)
    ),
    CONFESSOR(
            EAspect.PSIONIC, EAspect.LIGHT, EArmorType.MEDIUM,
            setOf(EWeaponStyle.GREAT, EWeaponStyle.RANGED),
            setOf(EWeaponCategory.BLUNT) // hammer doubles as ranged weapon
    ),
    REAVER(
            EAspect.PSIONIC, EAspect.ART, EArmorType.LIGHT,
            setOf(EWeaponStyle.GREAT),
            setOf(EWeaponCategory.BLADE)
    ),
    HERETIC(
            EAspect.SHADOW, EAspect.LIGHT, EArmorType.NONE,
            setOf(EWeaponStyle.UNARMED),
            setOf(EWeaponCategory.UNARMED)
    ),
    LURKER(
            EAspect.SHADOW, EAspect.ART, EArmorType.LIGHT,
            setOf(EWeaponStyle.DOUBLE, EWeaponStyle.RANGED),
            setOf(EWeaponCategory.MAGIC_WEAPON, EWeaponCategory.GLAIVE) // conjured double blade
    ),
    HELLION(
            EAspect.LIGHT, EAspect.ART, EArmorType.LIGHT,
            setOf(EWeaponStyle.DUAL),
            setOf(EWeaponCategory.BLADE)
    )
}