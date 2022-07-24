package cn.umacraft.mods.skill

import cn.umacraft.mods.skill.item.FatherSkill
import cn.umacraft.mods.skill.item.ItemRegistry
import cn.umacraft.mods.skill.item.TestSkill
import cn.umacraft.mods.skill.util.Const
import cn.umacraft.mods.skill.util.TempData
import net.minecraft.entity.passive.horse.AbstractChestedHorseEntity
import net.minecraft.potion.EffectInstance
import net.minecraft.potion.Effects
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.event.TickEvent.PlayerTickEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext

@Mod(Const.MOD_ID)
class Skill {
    init {
        FMLJavaModLoadingContext.get().modEventBus.apply {
            ItemRegistry.ITEMS.register(this)
        }

        MinecraftForge.EVENT_BUS.register(this)
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