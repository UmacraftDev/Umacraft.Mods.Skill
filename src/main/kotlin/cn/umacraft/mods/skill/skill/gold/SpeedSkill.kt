package cn.umacraft.mods.skill.skill.gold

import cn.umacraft.mods.skill.skill.FatherSkill
import cn.umacraft.mods.skill.skill.ISpeedable

class Speed1Skill : FatherSkill(), ISpeedable {
    override val speed: Int
        get() = 1

    override val registryTag: String
        get() = "gold_speed1"

    override val isPassive: Boolean
        get() = false
}

class Speed2Skill : FatherSkill(), ISpeedable {
    override val speed: Int
        get() = 2

    override val registryTag: String
        get() = "gold_speed2"

    override val isPassive: Boolean
        get() = false
}