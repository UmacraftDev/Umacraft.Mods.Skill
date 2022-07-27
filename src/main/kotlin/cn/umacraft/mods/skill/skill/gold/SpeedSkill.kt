package cn.umacraft.mods.skill.skill.gold

import cn.umacraft.mods.skill.skill.FatherSkill
import cn.umacraft.mods.skill.skill.ISpeedable

class Speed1Skill : FatherSkill(), ISpeedable {
    override fun getSpeed() = 1

    override val registryTag = "gold_speed1"
}

class Speed2Skill : FatherSkill(), ISpeedable {
    override fun getSpeed() = 2

    override val registryTag = "gold_speed2"
}