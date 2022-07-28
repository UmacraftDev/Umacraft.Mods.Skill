package cn.umacraft.mods.skill.skill.purple.road

import cn.umacraft.mods.skill.skill.FatherSkill
import cn.umacraft.mods.skill.skill.ISpeedable

class `68DebuffSkill` : FatherSkill(), ISpeedable {
    override val speed: Int
        get() = -1

    override val registryTag: String
        get() = "purple_68debuff"

    override val isPassive: Boolean
        get() = true
}