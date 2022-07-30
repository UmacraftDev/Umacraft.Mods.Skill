package cn.umacraft.mods.skill.skill.purple.road

import cn.umacraft.mods.skill.skill.FatherSkill
import cn.umacraft.mods.skill.skill.ISelfSpeedable

class `13DebuffSkill` : FatherSkill(), ISelfSpeedable {
    override val speed: Int
        get() = -1

    override val registryTag: String
        get() = "purple_13debuff"

    override val isPassive: Boolean
        get() = true
}