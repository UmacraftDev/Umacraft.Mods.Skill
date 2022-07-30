package cn.umacraft.mods.skill.skill.purple.weather

import cn.umacraft.mods.skill.skill.FatherSkill
import cn.umacraft.mods.skill.skill.ISelfSpeedable

class RainyDebuffSkill : FatherSkill(), ISelfSpeedable {
    override val speed: Int
        get() = -1

    override val registryTag: String
        get() = "purple_rainydebuff"

    override val isPassive: Boolean
        get() = true
}