package cn.umacraft.mods.skill.particle;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;

import java.awt.*;
import java.util.Locale;


public class SkillsParticleData implements IParticleData {
    private Vector3d speed;
    private Color color;
    private float diameter;
    public static final IDeserializer<SkillsParticleData> DESERIALIZER = new IDeserializer<SkillsParticleData>() {
        @Override
        public SkillsParticleData deserialize(ParticleType<SkillsParticleData> particleTypeIn, StringReader reader) throws CommandSyntaxException {
            final int MIN_COLOUR = 0;
            final int MAX_COLOUR = 255;
            reader.expect(' ');
            double speedX = reader.readDouble();
            reader.expect(' ');
            double speedY = reader.readDouble();
            reader.expect(' ');
            double speedZ = reader.readDouble();
            reader.expect(' ');
            int red = MathHelper.clamp(reader.readInt(), MIN_COLOUR, MAX_COLOUR);
            reader.expect(' ');
            int green = MathHelper.clamp(reader.readInt(), MIN_COLOUR, MAX_COLOUR);
            reader.expect(' ');
            int blue = MathHelper.clamp(reader.readInt(), MIN_COLOUR, MAX_COLOUR);
            reader.expect(' ');
            int alpha = MathHelper.clamp(reader.readInt(), 1, MAX_COLOUR);
            reader.expect(' ');
            float diameter = reader.readFloat();
            return new SkillsParticleData(new Vector3d(speedX, speedY, speedZ), new Color(red, green, blue, alpha), diameter);
        }

        @Override
        public SkillsParticleData read(ParticleType<SkillsParticleData> particleTypeIn, PacketBuffer buffer) {
            return new SkillsParticleData(new Vector3d(buffer.readDouble(), buffer.readDouble(), buffer.readDouble()), new Color(buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt()), buffer.readFloat());
        }
    };

    public SkillsParticleData(Vector3d speed, Color color, float diameter) {
        this.speed = speed;
        this.color = color;
        this.diameter = diameter;
    }

    @Override
    public ParticleType<?> getType() {
        return ParticleRegistry.skillParticle.get();
    }

    @Override
    public void write(PacketBuffer buffer){
        buffer.writeDouble(speed.x);
        buffer.writeDouble(speed.y);
        buffer.writeDouble(speed.z);
        buffer.writeInt(color.getRed());
        buffer.writeInt(color.getGreen());
        buffer.writeInt(color.getBlue());
        buffer.writeInt(color.getAlpha());
        buffer.writeFloat(diameter);
    }

    @Override
    public String getParameters() {
        return String.format(Locale.ROOT, "%s %.2f %i %i %i %i %.2d %.2d %.2d",
                this.getType().getRegistryName(), speed.x, speed.y, speed.z, color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha(), speed.getX(), speed.getY(), speed.getZ());
    }

    public Vector3d getSpeed() {
        return speed;
    }

    public Color getColor() {
        return color;
    }

    public float getDiameter() {
        return diameter;
    }
}
