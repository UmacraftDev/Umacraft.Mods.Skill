/*
 * Umacraft.Mods.Skill - A Skill mod for Umacraft Minecraft Server
 * https://github.com/UmacraftDev/Umacraft.Mods.Skill
 * Copyright (C) 2021-2022  UmacraftDev
 *
 * This Library is free software: you can redistribute it
 * and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will
 * be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public
 * License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program. If not,
 * see <https://www.gnu.org/licenses/>.
 *
 * Please contact us by email xray_yang@foxmail.com
 * if you need additional information or have any questions
 */
package cn.umacraft.mods.skill.skill.red.player

import cn.umacraft.mods.skill.skill.FatherSkill
import cn.umacraft.mods.skill.skill.IOtherPlayerEffect
import net.minecraft.potion.Effect
import net.minecraft.potion.Effects

class MagicianSkill : FatherSkill(), IOtherPlayerEffect {
    override val registryTag: String
        get() = "red_magician"

    override val isPassive: Boolean
        get() = false

    override val playerEffect: Effect
        get() = Effects.BLINDNESS

    override val level: Int
        get() = 1

    override val duration: Int
        get() = 20
}

class BlindfoldSkill : FatherSkill(), IOtherPlayerEffect {
    override val registryTag: String
        get() = "red_blindfold"

    override val isPassive: Boolean
        get() = false

    override val playerEffect: Effect
        get() = Effects.BLINDNESS

    override val level: Int
        get() = 1

    override val duration: Int
        get() = 10
}