package cn.umacraft.mods.skill.skill.green.weather

import cn.umacraft.mods.skill.skill.FatherSkill
import cn.umacraft.mods.skill.skill.ISelfSpeedable

class SunnyBuff1Skill : FatherSkill(), ISelfSpeedable {
    override val speed: Int
        get() = 1

    override val registryTag: String
        get() = "green_sunnybuff1"

    override val isPassive: Boolean
        get() = true
}

class SunnyBuff2Skill : FatherSkill(), ISelfSpeedable {
    override val speed: Int
        get() = 2

    override val registryTag: String
        get() = "green_sunnybuff2"

    override val isPassive: Boolean
        get() = true
}