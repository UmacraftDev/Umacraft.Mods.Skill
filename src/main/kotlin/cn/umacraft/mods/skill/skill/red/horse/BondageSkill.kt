package cn.umacraft.mods.skill.skill.red.horse

import cn.umacraft.mods.skill.skill.FatherSkill
import cn.umacraft.mods.skill.skill.IOtherSpeedable

class BondageSkill : FatherSkill(), IOtherSpeedable {
    override val speed: Int
        get() = -1

    override val registryTag: String
        get() = "red_bondage"

    override val isPassive: Boolean
        get() = false
}