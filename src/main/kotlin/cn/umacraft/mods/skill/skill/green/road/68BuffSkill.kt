package cn.umacraft.mods.skill.skill.green.road

import cn.umacraft.mods.skill.skill.FatherSkill
import cn.umacraft.mods.skill.skill.ISpeedable

class `68Buff1Skill` : FatherSkill(), ISpeedable {
    override fun getSpeed() = 1

    override val registryTag = "green_68buff1"
}

class `68Buff2Skill` : FatherSkill(), ISpeedable {
    override fun getSpeed() = 2

    override val registryTag = "green_68buff2"
}