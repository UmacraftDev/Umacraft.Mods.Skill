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
package cn.umacraft.mods.skill

import cn.umacraft.mods.skill.particle.SkillParticleFactory
import cn.umacraft.mods.skill.particle.SkillParticleType
import cn.umacraft.mods.skill.skill.*
import cn.umacraft.mods.skill.util.*
import net.minecraft.client.Minecraft
import net.minecraft.entity.passive.horse.AbstractHorseEntity
import net.minecraft.item.Item
import net.minecraft.potion.EffectInstance
import net.minecraft.potion.Effects
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.event.TickEvent.PlayerTickEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.Mod.EventBusSubscriber
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext
import net.minecraftforge.registries.DeferredRegister
import kotlin.math.absoluteValue

@Mod(MOD_ID)
class Skill {
    companion object {
        val SKILL_PARTICLE = PARTICLE_TYPES.register("skill_particle") { SkillParticleType() }!!
    }

    init {
        FMLJavaModLoadingContext.get().modEventBus.apply {
            ITEMS.register(this)
            PARTICLE_TYPES.register(this)
        }

        MinecraftForge.EVENT_BUS.register(this)

        ITEMS.registerAll()
    }

    @SubscribeEvent
    fun onPlayerTick(e: PlayerTickEvent) {
        val player = e.player
        val uuid = player.uniqueID
        val ridingEntity = player.ridingEntity

        if (TempData.PlayerSkillMap.containsKey(uuid)) {
            if (ridingEntity != null && ridingEntity is AbstractHorseEntity) {
                println("player: ${player.name.string}, riding, and used some skill")

                val skill = TempData.PlayerSkillMap[uuid] ?: return
                val skillType = skill.type

                // client side

                if (skillType is ISelfSpeedable) {
                    ridingEntity.addPotionEffect(
                        EffectInstance(
                            if (skillType.speed >= 0) Effects.SPEED else Effects.SLOWNESS,
                            if (skill.isPassive) 10000000 * 20 else 10 * 20,
                            skillType.speed.absoluteValue - 1
                        )
                    )
                    println("player ${player.name.string}, at ISelfSpeedable, added Effect of $skill")
                }

                // server side

                if (player.isServerWorld && player.server != null) {
                    val server = player.server!!
                    val playerList = server.playerList

                    val players =
                        playerList.players.filterNot { (it == player) || (it.name.string in playerList.oppedPlayerNames) }

                    println(mutableListOf<String>().apply {
                        players.forEach {
                            add(it.name.toString())
                        }
                    })

                    if (skillType is IOtherPlayerEffect) players.forEach {
                        it.addPotionEffect(
                            EffectInstance(
                                skillType.playerEffect,
                                skillType.duration * 20,
                                skillType.level - 1
                            )
                        )
                        println("player ${it.name.string}, at IOtherPlayerEffect, added Effect of $skill")
                    }

                    if (skillType is IOtherSpeedable) players.forEach {
                        val ridingEntity = it.ridingEntity
                        if (ridingEntity == null || ridingEntity !is AbstractHorseEntity) return@forEach

                        ridingEntity.addPotionEffect(
                            EffectInstance(
                                if (skillType.speed >= 0) Effects.SPEED else Effects.SLOWNESS,
                                if (skill.isPassive) 10000000 * 20 else 10 * 20,
                                skillType.speed.absoluteValue - 1
                            )
                        )
                        println("player ${it.name.string}, at IOtherSpeedable ,added Effect of $skill")
                    }
                }
            }
            TempData.PlayerSkillMap.remove(uuid)
        }
    }
}

fun DeferredRegister<Item>.registerAll() {
    SKILLS.forEach {
        val instance = FatherSkill(it)
        this.register(instance.registryTag) { instance }
    }
}

@EventBusSubscriber(modid = MOD_ID, value = [Dist.CLIENT], bus = EventBusSubscriber.Bus.MOD)
object ParticleFactoryRegistry {
    @SubscribeEvent
    fun registerParticleFactory(event: ParticleFactoryRegisterEvent?) {
        Minecraft.getInstance().particles.registerFactory(
            Skill.SKILL_PARTICLE.get(),
            SkillParticleFactory::class.java.newInstance()
        )
    }
}