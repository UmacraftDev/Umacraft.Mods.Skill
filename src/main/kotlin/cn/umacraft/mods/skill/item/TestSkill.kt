package cn.umacraft.mods.skill.item

import cn.umacraft.mods.skill.util.TempData
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Food
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.potion.EffectInstance
import net.minecraft.potion.Effects
import net.minecraft.world.World

class TestSkill : Item(
    Properties()
        .group(ModGroup.itemGroup)
        .food(
            Food.Builder()
                .fastToEat()
                .effect({
                    return@effect EffectInstance(Effects.SATURATION, 1 * 20, 1)
                }, 1f)
                .build()
        )
        .maxStackSize(1)
), FatherSkill {
    override fun onItemUseFinish(stack: ItemStack, worldIn: World, entityLiving: LivingEntity): ItemStack {
        if (entityLiving is PlayerEntity) {
            TempData.PlayerSkillMap[entityLiving.uniqueID] = this
        }

        return super.onItemUseFinish(stack, worldIn, entityLiving)
    }
}