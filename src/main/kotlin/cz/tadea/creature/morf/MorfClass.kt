package main.kotlin.cz.tadea.creature.morf

import main.kotlin.cz.tadea.creature.enums.EArmorType

enum class MorfClass(
        val aspectA: EAspect,
        val aspectB: EAspect
) {
    BLADEMASTER(
            EAspect.COMBAT, EAspect.COMBAT
    ),
    DRUID(
            EAspect.NATURE, EAspect.NATURE
    ),
    LOREKEEPER(
            EAspect.ARCANE, EAspect.ARCANE
    ),
    BISHOP(
            EAspect.SPIRIT, EAspect.SPIRIT
    ),
    MESMER(
            EAspect.PSIONIC, EAspect.PSIONIC
    ),
    STALKER(
            EAspect.SHADOW, EAspect.SHADOW
    ),
    SEER(
            EAspect.LIGHT, EAspect.LIGHT
    ),
    ROGUE(
            EAspect.ART, EAspect.ART
    ),
    RANGER(
            EAspect.COMBAT, EAspect.NATURE
    ),
    TEMPEST(
            EAspect.COMBAT, EAspect.ARCANE
    ),
    SEEKER(
            EAspect.COMBAT, EAspect.SPIRIT
    ),
    GUARDIAN(
            EAspect.COMBAT, EAspect.PSIONIC
    ),
    INQUISITOR(
            EAspect.COMBAT, EAspect.SHADOW
    ),
    VINDICATOR(
            EAspect.COMBAT, EAspect.LIGHT
    ),
    MYRMIDON(
            EAspect.COMBAT, EAspect.ART
    ),
    WARDEN(
            EAspect.NATURE, EAspect.ARCANE
    ),
    MYSTIC(
            EAspect.NATURE, EAspect.SPIRIT
    ),
    KESTREL(
            EAspect.NATURE, EAspect.PSIONIC
    ),
    AVENGER(
            EAspect.NATURE, EAspect.SHADOW
    ),
    CELEBRANT(
            EAspect.NATURE, EAspect.LIGHT
    ),
    STRIDER(
            EAspect.NATURE, EAspect.ART
    ),
    EIDOLON(
            EAspect.ARCANE, EAspect.SPIRIT
    ),
    RIDDLER(
            EAspect.ARCANE, EAspect.PSIONIC
    ),
    SHIFTER(
            EAspect.ARCANE, EAspect.SHADOW
    ),
    ARBITER(
            EAspect.ARCANE, EAspect.LIGHT
    ),
    SCRIVENER(
            EAspect.ARCANE, EAspect.ART
    ),
    REDEEMER(
            EAspect.SPIRIT, EAspect.PSIONIC
    ),
    WRAITH(
            EAspect.SPIRIT, EAspect.SHADOW
    ),
    DIVINER(
            EAspect.SPIRIT, EAspect.LIGHT
    ),
    ARCHON(
            EAspect.SPIRIT, EAspect.ART
    ),
    SENTINEL(
            EAspect.PSIONIC, EAspect.SHADOW
    ),
    CONFESSOR(
            EAspect.PSIONIC, EAspect.LIGHT
    ),
    REAVER(
            EAspect.PSIONIC, EAspect.ART
    ),
    HERETIC(
            EAspect.SHADOW, EAspect.LIGHT
    ),
    LURKER(
            EAspect.SHADOW, EAspect.ART
    ),
    HELLION(
            EAspect.LIGHT, EAspect.ART
    )
}