package cn.umacraft.mods.skill.skill.green.road

import cn.umacraft.mods.skill.skill.FatherSkill
import cn.umacraft.mods.skill.skill.ISelfSpeedable

class `68Buff1Skill` : FatherSkill(), ISelfSpeedable {
    override val speed: Int
        get() = 1

    override val registryTag: String
        get() = "green_68buff1"

    override val isPassive: Boolean
        get() = true
}

class `68Buff2Skill` : FatherSkill(), ISelfSpeedable {
    override val speed: Int
        get() = 2

    override val registryTag: String
        get() = "green_68buff2"

    override val isPassive: Boolean
        get() = true
}