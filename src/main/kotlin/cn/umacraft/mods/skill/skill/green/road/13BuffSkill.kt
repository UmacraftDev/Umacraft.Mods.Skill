package cn.umacraft.mods.skill.skill.green.road

import cn.umacraft.mods.skill.skill.FatherSkill
import cn.umacraft.mods.skill.skill.ISpeedable

class `13Buff1Skill` : FatherSkill(), ISpeedable {
    override fun getSpeed() = 1

    override val registryTag = "green_13buff1"
}

class `13Buff2Skill` : FatherSkill(), ISpeedable {
    override fun getSpeed() = 2

    override val registryTag = "green_13buff2"
}