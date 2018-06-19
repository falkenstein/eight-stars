package main.kotlin.cz.tadea.creature.company

import main.kotlin.cz.tadea.player.Player
import main.kotlin.cz.tadea.singleton.TemplateStore
import main.kotlin.cz.tadea.template.CreatureTemplate

/**
 * Company - consists of a small amount of creatures. Doesn't figure on tactical layer, as there it is just about individual creatures.
 */
class Company(
        /**
         * Player that owns this company.
         */
        val owner: Player,
        /**
         * Front line is composed of two pairs of the same creature type (of course all four can be also the same).
         */
        val front: List<CreatureTemplate>,
        /**
         * No limitations apply to the back line creature type.
         */
        val back: List<CreatureTemplate>,
        /**
         * The commander of the company, mandatory (wasn't always mandatory in the setting).
         */
        val commander: CreatureTemplate
) {
    constructor(player: Player, creatures: List<String>) : this(
            player,
            front = creatures.take(4).map { TemplateStore.getCreature(it) },
            back = creatures.drop(5).map { TemplateStore.getCreature(it) }, // Drops 4 front creatures and commander
            commander = TemplateStore.getCreature(creatures[4])
    )
}