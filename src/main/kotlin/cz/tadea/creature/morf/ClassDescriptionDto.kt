package main.kotlin.cz.tadea.creature.morf

import com.fasterxml.jackson.annotation.JsonProperty
import main.kotlin.cz.tadea.skill.SkillType

data class ClassDescriptionDto(
        @JsonProperty("class")
        val `class`: MorfClass,
        @JsonProperty("grandmaster")
        val grandmaster: List<SkillType>,
        @JsonProperty("master")
        val master: List<SkillType>,
        @JsonProperty("expert")
        val expert: List<SkillType>,
        @JsonProperty("basic")
        val basic: List<SkillType>
)