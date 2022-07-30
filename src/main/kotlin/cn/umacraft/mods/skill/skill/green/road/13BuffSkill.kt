package cn.umacraft.mods.skill.skill.green.road

import cn.umacraft.mods.skill.skill.FatherSkill
import cn.umacraft.mods.skill.skill.ISelfSpeedable

class `13Buff1Skill` : FatherSkill(), ISelfSpeedable {
    override val speed: Int
        get() = 1

    override val registryTag: String
        get() = "green_13buff1"

    override val isPassive: Boolean
        get() = true
}

class `13Buff2Skill` : FatherSkill(), ISelfSpeedable {
    override val speed: Int
        get() = 2

    override val registryTag: String
        get() = "green_13buff2"

    override val isPassive: Boolean
        get() = true
}