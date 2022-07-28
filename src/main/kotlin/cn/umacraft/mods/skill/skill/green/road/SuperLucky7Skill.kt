package cn.umacraft.mods.skill.skill.green.road

import cn.umacraft.mods.skill.skill.FatherSkill
import cn.umacraft.mods.skill.skill.ISpeedable

class SuperLucky7Skill : FatherSkill(), ISpeedable {
    override val speed: Int
        get() = 2

    override val registryTag: String
        get() = "green_superlucky7"

    override val isPassive: Boolean
        get() = true
}