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

import cn.umacraft.mods.skill.util.TempData
import cn.umacraft.mods.skill.util.itemGroup
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Food
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.potion.Effect
import net.minecraft.world.World

abstract class FatherSkill : Item(
    Properties()
        .group(itemGroup)
        .food(
            Food.Builder()
                .fastToEat()
                .build()
        )
        .maxStackSize(1)
) {
    abstract val registryTag: String

    abstract val isPassive: Boolean

    override fun onItemUseFinish(stack: ItemStack, worldIn: World, entityLiving: LivingEntity): ItemStack {
        if (entityLiving is PlayerEntity) {
            TempData.PlayerSkillMap[entityLiving.uniqueID] = this
        }

        return super.onItemUseFinish(stack, worldIn, entityLiving)
    }

    override fun onUse(worldIn: World, livingEntityIn: LivingEntity, stack: ItemStack, count: Int) {
        super.onUse(worldIn, livingEntityIn, stack, count)
    }


}

interface ISelfSpeedable {
    val speed: Int
}

interface IOtherSpeedable {
    val speed: Int
}

interface IOtherPlayerEffect {
    val playerEffect: Effect

    val duration: Int

    val level: Int
}