package cn.umacraft.mods.skill.skill.green.weather

import cn.umacraft.mods.skill.skill.FatherSkill
import cn.umacraft.mods.skill.skill.ISpeedable

class RainyBuff1Skill : FatherSkill(), ISpeedable {
    override val speed: Int
        get() = 1

    override val registryTag: String
        get() = "green_rainybuff1"

    override val isPassive: Boolean
        get() = true
}

class RainyBuff2Skill : FatherSkill(), ISpeedable {
    override val speed: Int
        get() = 2

    override val registryTag: String
        get() = "green_rainybuff2"

    override val isPassive: Boolean
        get() = true
}