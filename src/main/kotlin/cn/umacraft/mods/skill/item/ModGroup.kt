package cn.umacraft.mods.skill.item

import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack

object ModGroup {
    val itemGroup = object : ItemGroup("uc_skills") {
        override fun createIcon(): ItemStack = ItemStack(ItemRegistry.testPaper.get())
    }
}