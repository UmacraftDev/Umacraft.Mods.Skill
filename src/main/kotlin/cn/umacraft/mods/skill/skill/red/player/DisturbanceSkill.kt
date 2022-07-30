package cn.umacraft.mods.skill.skill.red.player

import cn.umacraft.mods.skill.skill.FatherSkill
import cn.umacraft.mods.skill.skill.IOtherPlayerEffect
import net.minecraft.potion.Effect
import net.minecraft.potion.Effects

class DisturbanceOfIllusionSkill : FatherSkill(), IOtherPlayerEffect {
    override val registryTag: String
        get() = "red_disturbance_of_illusion"

    override val isPassive: Boolean
        get() = false

    override val playerEffect: Effect
        get() = Effects.BLINDNESS

    override val level: Int
        get() = 1

    override val duration: Int
        get() = 20
}

class DisturbanceSkill : FatherSkill(), IOtherPlayerEffect {
    override val registryTag: String
        get() = "red_disturbance"

    override val isPassive: Boolean
        get() = false

    override val playerEffect: Effect
        get() = Effects.BLINDNESS

    override val level: Int
        get() = 1

    override val duration: Int
        get() = 10
}