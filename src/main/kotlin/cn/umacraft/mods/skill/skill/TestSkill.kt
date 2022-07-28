package cn.umacraft.mods.skill.skill

import net.minecraft.potion.Effect
import net.minecraft.potion.Effects

class TestSkill : FatherSkill(), ISpeedable, IPlayerEffect {
    override val speed: Int
        get() = 1

    override val registryTag: String
        get() = "test_skill"

    override val isPassive: Boolean
        get() = false

    override val duration: Int
        get() = 10

    override val level: Int
        get() = 1

    override val playerEffect: Effect
        get() = Effects.BLINDNESS
}