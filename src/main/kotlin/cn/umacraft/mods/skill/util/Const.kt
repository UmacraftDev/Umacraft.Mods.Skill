package cn.umacraft.mods.skill.util

import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries

const val MOD_ID = "skill"
val itemGroup = object : ItemGroup("uc_skills") {
    override fun createIcon(): ItemStack = ItemStack(Items.PAPER)
}
val ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID)!!