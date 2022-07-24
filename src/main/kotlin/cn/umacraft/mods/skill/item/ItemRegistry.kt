package cn.umacraft.mods.skill.item

import cn.umacraft.mods.skill.util.Const
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries

object ItemRegistry {
    val ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Const.MOD_ID)!!
    val testPaper = ITEMS.register("test_skill") { TestSkill() }
}