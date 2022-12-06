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
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.world.World

abstract class FatherSkill : Item(
    Properties()
        .group(itemGroup)
        .maxStackSize(16)
) {
    abstract val type: FatherSkillType

    abstract val registryTag: String

    abstract val isPassive: Boolean

    override fun onItemRightClick(worldIn: World, playerIn: PlayerEntity, handIn: Hand): ActionResult<ItemStack> {
        val itemStack = playerIn.getHeldItem(handIn)
        playerIn.activeHand = handIn
        TempData.PlayerSkillMap[playerIn.uniqueID] = this
        itemStack.count -= 1
        return ActionResult.resultConsume(itemStack)
    }

    companion object {
        operator fun invoke(dataholder: SkillInfoPlaceholder): FatherSkill {
            return object : FatherSkill() {
                override val type: FatherSkillType
                    get() = dataholder.type

                override val registryTag: String
                    get() = dataholder.registryTag

                override val isPassive: Boolean
                    get() = dataholder.isPassive
            }
        }
    }
}

data class SkillInfoPlaceholder(
    val type: FatherSkillType,
    val registryTag: String,
    val isPassive: Boolean
)