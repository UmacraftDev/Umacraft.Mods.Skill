package cn.umacraft.mods.skill

import cn.umacraft.mods.skill.skill.FatherSkill
import cn.umacraft.mods.skill.skill.IOtherPlayerEffect
import cn.umacraft.mods.skill.skill.IOtherSpeedable
import cn.umacraft.mods.skill.skill.ISelfSpeedable
import cn.umacraft.mods.skill.skill.gold.Speed1Skill
import cn.umacraft.mods.skill.skill.gold.Speed2Skill
import cn.umacraft.mods.skill.skill.green.road.*
import cn.umacraft.mods.skill.skill.green.weather.*
import cn.umacraft.mods.skill.skill.purple.road.*
import cn.umacraft.mods.skill.skill.purple.weather.RainyDebuffSkill
import cn.umacraft.mods.skill.skill.purple.weather.SnowyDebuffSkill
import cn.umacraft.mods.skill.skill.purple.weather.SunnyDebuffSkill
import cn.umacraft.mods.skill.skill.red.horse.BondageSkill
import cn.umacraft.mods.skill.skill.red.horse.MonopolySkill
import cn.umacraft.mods.skill.skill.red.player.BlindfoldSkill
import cn.umacraft.mods.skill.skill.red.player.DisturbanceOfIllusionSkill
import cn.umacraft.mods.skill.skill.red.player.DisturbanceSkill
import cn.umacraft.mods.skill.skill.red.player.MagicianSkill
import cn.umacraft.mods.skill.util.ITEMS
import cn.umacraft.mods.skill.util.MOD_ID
import cn.umacraft.mods.skill.util.TempData
import net.minecraft.entity.passive.horse.AbstractHorseEntity
import net.minecraft.item.Item
import net.minecraft.potion.EffectInstance
import net.minecraft.potion.Effects
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.event.TickEvent.PlayerTickEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext
import net.minecraftforge.registries.DeferredRegister
import kotlin.math.absoluteValue

@Mod(MOD_ID)
class Skill {
    init {
        FMLJavaModLoadingContext.get().modEventBus.apply {
            ITEMS.register(this)
        }

        MinecraftForge.EVENT_BUS.register(this)

        ITEMS.registerAll(
            listOf(
                // TestSkill::class.java,
                Speed1Skill::class.java,
                Speed2Skill::class.java,

                
                `13Buff1Skill`::class.java,
                `13Buff2Skill`::class.java,

                `68Buff1Skill`::class.java,
                `68Buff2Skill`::class.java,

                LeftBuff1Skill::class.java,
                LeftBuff2Skill::class.java,

                RightBuff1Skill::class.java,
                RightBuff2Skill::class.java,

                SuperLucky7Skill::class.java,


                SnowyBuff1Skill::class.java,
                SnowyBuff2Skill::class.java,


                RainyBuff1Skill::class.java,
                RainyBuff2Skill::class.java,


                SunnyBuff1Skill::class.java,
                SunnyBuff2Skill::class.java,


                SnowyDebuffSkill::class.java,
                RainyDebuffSkill::class.java,
                SunnyDebuffSkill::class.java,
                LeftDebuffSkill::class.java,
                RightDebuffSkill::class.java,
                `13DebuffSkill`::class.java,
                `68DebuffSkill`::class.java,


                MonopolySkill::class.java,
                BondageSkill::class.java,


                MagicianSkill::class.java,
                BlindfoldSkill::class.java,
                DisturbanceOfIllusionSkill::class.java,
                DisturbanceSkill::class.java
            )
        )
    }

    @SubscribeEvent
    fun onPlayerTick(e: PlayerTickEvent) {
        val player = e.player
        val uuid = player.uniqueID
        val ridingEntity = player.ridingEntity

        if (TempData.PlayerSkillMap.containsKey(uuid)) {
            if (ridingEntity != null && ridingEntity is AbstractHorseEntity) {
                println("player: ${player.name.string}, riding, and used some skill")

                val skill = TempData.PlayerSkillMap[uuid]

                // client side

                if (skill is ISelfSpeedable) {
                    ridingEntity.addPotionEffect(
                        EffectInstance(
                            if (skill.speed >= 0) Effects.SPEED else Effects.SLOWNESS,
                            if (skill.isPassive) 10000000 * 20 else 10 * 20,
                            skill.speed.absoluteValue - 1
                        )
                    ); println("player ${player.name.string}, at ISelfSpeedable, added Effect of $skill")
                }

                // server side

                if (player.isServerWorld && player.server != null) {
                    val server = player.server!!
                    val playerList = server.playerList
                    val players = playerList.players.toMutableList().apply {
                        remove(player)
                        forEach {
                            if (it.name.string in playerList.oppedPlayerNames) {
                                remove(it)
                            }
                        }
                    }

                    println(players)

                    if (skill is IOtherPlayerEffect) players.forEach {
                        it.addPotionEffect(
                            EffectInstance(
                                skill.playerEffect,
                                skill.duration * 20,
                                skill.level - 1
                            )
                        )
                        println("player ${it.name.string}, at IOtherPlayerEffect, added Effect of $skill")
                    }

                    if (skill is IOtherSpeedable) players.forEach {
                        val ridingEntity = it.ridingEntity
                        if (ridingEntity == null || ridingEntity !is AbstractHorseEntity) return@forEach

                        ridingEntity.addPotionEffect(
                            EffectInstance(
                                if (skill.speed >= 0) Effects.SPEED else Effects.SLOWNESS,
                                if (skill.isPassive) 10000000 * 20 else 10 * 20,
                                skill.speed.absoluteValue - 1
                            )
                        )
                        println("player ${it.name.string}, at IOtherSpeedable ,added Effect of $skill")
                    }
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