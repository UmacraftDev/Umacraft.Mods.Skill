package cn.umacraft.mods.skill

import cn.umacraft.mods.skill.skill.FatherSkill
import cn.umacraft.mods.skill.skill.TestSkill
import cn.umacraft.mods.skill.skill.gold.Speed1Skill
import cn.umacraft.mods.skill.skill.gold.Speed2Skill
import cn.umacraft.mods.skill.skill.green.road.*
import cn.umacraft.mods.skill.util.ITEMS
import cn.umacraft.mods.skill.util.MOD_ID
import cn.umacraft.mods.skill.util.TempData
import net.minecraft.entity.passive.horse.AbstractChestedHorseEntity
import net.minecraft.item.Item
import net.minecraft.potion.EffectInstance
import net.minecraft.potion.Effects
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.event.TickEvent.PlayerTickEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext
import net.minecraftforge.registries.DeferredRegister

@Mod(MOD_ID)
class Skill {
    init {
        FMLJavaModLoadingContext.get().modEventBus.apply {
            ITEMS.register(this)
        }

        MinecraftForge.EVENT_BUS.register(this)

        ITEMS.registerAll(
            listOf(
                TestSkill::class.java,

                Speed1Skill::class.java,
                Speed2Skill::class.java,

                `13Buff1Skill`::class.java,
                `13Buff2Skill`::class.java,
                `68Buff1Skill`::class.java,
                `68Buff2Skill`::class.java
            )
        )
    }

    @SubscribeEvent
    fun onRide(e: PlayerTickEvent) {
        val player = e.player
        val uuid = player.uniqueID
        val ridingEntity = player.ridingEntity

        if (TempData.PlayerSkillMap.containsKey(uuid)) {
            if (ridingEntity != null && ridingEntity is AbstractChestedHorseEntity) {
                when (TempData.PlayerSkillMap[uuid]) {
                    is TestSkill -> ridingEntity.addPotionEffect(EffectInstance(Effects.SPEED, 5 * 20, 5))
                }
            }
            TempData.PlayerSkillMap.remove(uuid)
        }

        if (!player.heldItemMainhand.isEmpty
            && player.heldItemMainhand.item is FatherSkill
            && !player.foodStats.needFood()
        ) {
            player.foodStats.foodLevel -= 1
        }
    }
}

fun DeferredRegister<Item>.registerAll(list: List<Class<out FatherSkill>>) {
    list.forEach {
        val instance = it.newInstance()
        this.register(instance.registryTag) { instance }
    }
}