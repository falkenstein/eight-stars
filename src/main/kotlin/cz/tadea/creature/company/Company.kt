package cz.tadea.creature.company

import cz.tadea.template.CreatureTemplate

/**
 * Company - consists of a small amount of creatures. Doesn't figure on tactical layer, as there it is just about individual creatures.
 */
class Company(
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
}