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

import cn.umacraft.mods.skill.util.SKILL_PARTICLE
import com.mojang.brigadier.StringReader
import com.mojang.brigadier.exceptions.CommandSyntaxException
import net.minecraft.network.PacketBuffer
import net.minecraft.particles.IParticleData
import net.minecraft.particles.IParticleData.IDeserializer
import net.minecraft.particles.ParticleType
import net.minecraft.util.math.MathHelper
import net.minecraft.util.math.vector.Vector3d
import java.awt.Color
import java.util.*

class SkillParticleData(val speed: Vector3d, val color: Color, val diameter: Float) : IParticleData {
    override fun getType(): ParticleType<*> {
        return SKILL_PARTICLE.get()
    }

    override fun write(buffer: PacketBuffer) {
        buffer.writeDouble(speed.x)
        buffer.writeDouble(speed.y)
        buffer.writeDouble(speed.z)
        buffer.writeInt(color.red)
        buffer.writeInt(color.green)
        buffer.writeInt(color.blue)
        buffer.writeInt(color.alpha)
        buffer.writeFloat(diameter)
    }

    override fun getParameters(): String {
        return String.format(
            Locale.ROOT,
            "%s %.2f %i %i %i %i %.2d %.2d %.2d",
            this.type.registryName,
            speed.x,
            speed.y,
            speed.z,
            color.red,
            color.green,
            color.blue,
            color.alpha,
            speed.getX(),
            speed.getY(),
            speed.getZ()
        )
    }

    companion object {
        val DESERIALIZER = object : IDeserializer<SkillParticleData> {
            @Throws(CommandSyntaxException::class)
            override fun deserialize(
                particleTypeIn: ParticleType<SkillParticleData>,
                reader: StringReader
            ): SkillParticleData {
                val MIN_COLOUR = 0
                val MAX_COLOUR = 255
                reader.expect(' ')
                val speedX = reader.readDouble()
                reader.expect(' ')
                val speedY = reader.readDouble()
                reader.expect(' ')
                val speedZ = reader.readDouble()
                reader.expect(' ')
                val red = MathHelper.clamp(reader.readInt(), MIN_COLOUR, MAX_COLOUR)
                reader.expect(' ')
                val green = MathHelper.clamp(reader.readInt(), MIN_COLOUR, MAX_COLOUR)
                reader.expect(' ')
                val blue = MathHelper.clamp(reader.readInt(), MIN_COLOUR, MAX_COLOUR)
                reader.expect(' ')
                val alpha = MathHelper.clamp(reader.readInt(), 1, MAX_COLOUR)
                reader.expect(' ')
                val diameter = reader.readFloat()
                return SkillParticleData(Vector3d(speedX, speedY, speedZ), Color(red, green, blue, alpha), diameter)
            }

            override fun read(
                particleTypeIn: ParticleType<SkillParticleData>,
                buffer: PacketBuffer
            ): SkillParticleData {
                return SkillParticleData(
                    Vector3d(buffer.readDouble(), buffer.readDouble(), buffer.readDouble()),
                    Color(buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt()),
                    buffer.readFloat()
                )
            }
        }
    }
}