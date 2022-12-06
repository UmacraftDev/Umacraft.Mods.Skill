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
package cn.umacraft.mods.skill.skill

import net.minecraft.potion.Effect
import net.minecraft.potion.Effects

private val POSITIVE_SKILLS_ID = listOf(
    "gold_speed",
    "green_13buff",
    "green_68buff",
    "green_leftbuff",
    "green_rightbuff",
    "green_rainybuff",
    "green_snowybuff",
    "green_sunnybuff"
)

private val POSITIVE_SKILLS_BASE: (String) -> List<SkillInfoPlaceholder> = {
    listOf(
        SkillInfoPlaceholder(
            object : ISelfSpeedable {
                override val speed: Int
                    get() = 1
            },
            "${it}1",
            !it.startsWith("gold")
        ),
        SkillInfoPlaceholder(
            object : ISelfSpeedable {
                override val speed: Int
                    get() = 2
            },
            "${it}2",
            !it.startsWith("gold")
        )
    )
}

private val NEGATIVE_SKILLS_ID = listOf(
    "purple_13debuff",
    "purple_68debuff",
    "purple_leftdebuff",
    "purple_rightdebuff",
    "purple_rainydebuff",
    "purple_snowydebuff",
    "purple_sunnydebuff"
)

private val NEGATIVE_SKILL_BASE: (String) -> List<SkillInfoPlaceholder> = {
    listOf(
        SkillInfoPlaceholder(
            object : ISelfSpeedable {
                override val speed: Int
                    get() = -1
            },
            it,
            true
        )
    )
}

private val RED_SKILLS = listOf(
    SkillInfoPlaceholder(
        object : IOtherSpeedable {
            override val speed: Int
                get() = -1
        },
        "red_bondage",
        false
    ),
    SkillInfoPlaceholder(
        object : IOtherSpeedable {
            override val speed: Int
                get() = -2
        },
        "red_monopoly",
        false
    ),

    SkillInfoPlaceholder(
        object : IOtherPlayerEffect {
            override val playerEffect: Effect
                get() = Effects.BLINDNESS

            override val level: Int
                get() = 1

            override val duration: Int
                get() = 10
        },
        "red_disturbance",
        false
    ),
    SkillInfoPlaceholder(
        object : IOtherPlayerEffect {
            override val playerEffect: Effect
                get() = Effects.BLINDNESS

            override val level: Int
                get() = 1

            override val duration: Int
                get() = 20
        },
        "red_disturbance_of_illusion",
        false
    ),

    SkillInfoPlaceholder(
        object : IOtherPlayerEffect {
            override val playerEffect: Effect
                get() = Effects.BLINDNESS

            override val level: Int
                get() = 1

            override val duration: Int
                get() = 10
        },
        "red_blindfold",
        false
    ),
    SkillInfoPlaceholder(
        object : IOtherPlayerEffect {
            override val playerEffect: Effect
                get() = Effects.BLINDNESS

            override val level: Int
                get() = 1

            override val duration: Int
                get() = 20
        },
        "red_magician",
        false
    )
)

private fun makeSkillList(): List<SkillInfoPlaceholder> {
    val list = mutableListOf<SkillInfoPlaceholder>()

    POSITIVE_SKILLS_ID.forEach {
        list.addAll(POSITIVE_SKILLS_BASE(it))
    }

    NEGATIVE_SKILLS_ID.forEach {
        list.addAll(NEGATIVE_SKILL_BASE(it))
    }

    list.addAll(RED_SKILLS)

    return list
}

val SKILLS = makeSkillList()