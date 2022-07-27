package cn.umacraft.mods.skill.skill

import cn.umacraft.mods.skill.util.TempData
import cn.umacraft.mods.skill.util.itemGroup
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Food
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
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

    override fun onItemUseFinish(stack: ItemStack, worldIn: World, entityLiving: LivingEntity): ItemStack {
        if (entityLiving is PlayerEntity) {
            TempData.PlayerSkillMap[entityLiving.uniqueID] = this
        }

        return super.onItemUseFinish(stack, worldIn, entityLiving)
    }
}

interface ISpeedable {
    fun getSpeed(): Int
}