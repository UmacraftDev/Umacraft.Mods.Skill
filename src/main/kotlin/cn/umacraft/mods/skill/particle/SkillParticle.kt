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
package cn.umacraft.mods.skill.particle

import com.mojang.serialization.Codec
import net.minecraft.client.particle.IParticleRenderType
import net.minecraft.client.particle.SpriteTexturedParticle
import net.minecraft.client.world.ClientWorld
import net.minecraft.particles.ParticleType
import net.minecraft.util.math.vector.Vector3d
import java.awt.Color

class SkillParticle(
    world: ClientWorld?,
    x: Double,
    y: Double,
    z: Double,
    speed: Vector3d,
    color: Color?,
    diameter: Float
) :
    SpriteTexturedParticle(world!!, x, y, z, speed.x, speed.y, speed.z) {
    init {
        maxAge = 100
        motionX = speed.x
        motionY = speed.y
        motionZ = speed.z
        val PARTICLE_SCALE_FOR_ONE_METRE = 0.5f
        particleScale = PARTICLE_SCALE_FOR_ONE_METRE * diameter

        // 粒子颜色和透明度我没设置，先跑一下试试
    }

    override fun getRenderType(): IParticleRenderType {
        return IParticleRenderType.PARTICLE_SHEET_TRANSLUCENT
    }
}


class SkillParticleType : ParticleType<SkillParticleData>(false, SkillParticleData.DESERIALIZER) {
    override fun func_230522_e_(): Codec<SkillParticleData>? {
        return null
    }
}

