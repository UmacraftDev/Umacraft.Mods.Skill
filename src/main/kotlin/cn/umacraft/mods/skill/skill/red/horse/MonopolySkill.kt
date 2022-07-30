package cn.umacraft.mods.skill.skill.red.horse

import cn.umacraft.mods.skill.skill.FatherSkill
import cn.umacraft.mods.skill.skill.IOtherSpeedable

class MonopolySkill : FatherSkill(), IOtherSpeedable {
    override val speed: Int
        get() = -2

    override val registryTag: String
        get() = "red_monopoly"

    override val isPassive: Boolean
        get() = false
}