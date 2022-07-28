package cn.umacraft.mods.skill.skill.purple.road

import cn.umacraft.mods.skill.skill.FatherSkill
import cn.umacraft.mods.skill.skill.ISpeedable

class LeftDebuffSkill : FatherSkill(), ISpeedable {
    override val speed: Int
        get() = -1

    override val registryTag: String
        get() = "purple_leftdebuff"

    override val isPassive: Boolean
        get() = true
}