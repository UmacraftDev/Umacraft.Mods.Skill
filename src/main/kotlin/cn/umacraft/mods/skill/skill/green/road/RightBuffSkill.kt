package cn.umacraft.mods.skill.skill.green.road

import cn.umacraft.mods.skill.skill.FatherSkill
import cn.umacraft.mods.skill.skill.ISpeedable

class RightBuff1Skill : FatherSkill(), ISpeedable {
    override val speed: Int
        get() = 1

    override val registryTag: String
        get() = "green_rightbuff1"

    override val isPassive: Boolean
        get() = true
}

class RightBuff2Skill : FatherSkill(), ISpeedable {
    override val speed: Int
        get() = 2

    override val registryTag: String
        get() = "green_rightbuff2"

    override val isPassive: Boolean
        get() = true
}