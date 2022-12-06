package cn.umacraft.mods.skill.particle;

import com.mojang.serialization.Codec;
import net.minecraft.particles.ParticleType;
import org.jetbrains.annotations.NotNull;

public class SkillsParticleType extends ParticleType<SkillsParticleData> {
    public SkillsParticleType() {

        super(false, SkillsParticleData.DESERIALIZER);
    }

    @NotNull
    @Override
    public Codec<SkillsParticleData> func_230522_e_() {
        return null;
    }
}

