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

import net.minecraft.client.particle.IAnimatedSprite
import net.minecraft.client.particle.IParticleFactory
import net.minecraft.client.particle.Particle
import net.minecraft.client.world.ClientWorld

class SkillParticleFactory(private val sprites: IAnimatedSprite) : IParticleFactory<SkillParticleData> {
    override fun makeParticle(
        data: SkillParticleData,
        world: ClientWorld,
        x: Double,
        y: Double,
        z: Double,
        xSpeed: Double,
        ySpeed: Double,
        zSpeed: Double
    ): Particle {
        //particle.selectSpriteRandomly(sprites); 因为目前还没做材质
        return SkillParticle(world, x, y, z, data.speed, data.color, data.diameter)
    }
}