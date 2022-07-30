package cn.umacraft.mods.skill.skill.green.road

import cn.umacraft.mods.skill.skill.FatherSkill
import cn.umacraft.mods.skill.skill.ISelfSpeedable

class LeftBuff1Skill : FatherSkill(), ISelfSpeedable {
    override val speed: Int
        get() = 1

    override val registryTag: String
        get() = "green_leftbuff1"

    override val isPassive: Boolean
        get() = true
}

class LeftBuff2Skill : FatherSkill(), ISelfSpeedable {
    override val speed: Int
        get() = 2

    override val registryTag: String
        get() = "green_leftbuff2"

    override val isPassive: Boolean
        get() = true
}