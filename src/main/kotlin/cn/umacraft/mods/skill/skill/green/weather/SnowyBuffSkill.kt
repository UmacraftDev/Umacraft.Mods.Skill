package cn.umacraft.mods.skill.skill.green.weather

import cn.umacraft.mods.skill.skill.FatherSkill
import cn.umacraft.mods.skill.skill.ISpeedable

class SnowyBuff1Skill : FatherSkill(), ISpeedable {
    override val speed: Int
        get() = 1

    override val registryTag: String
        get() = "green_snowybuff1"

    override val isPassive: Boolean
        get() = true
}

class SnowyBuff2Skill : FatherSkill(), ISpeedable {
    override val speed: Int
        get() = 2

    override val registryTag: String
        get() = "green_snowybuff2"

    override val isPassive: Boolean
        get() = true
}