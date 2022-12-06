package cn.umacraft.mods.skill.particle;

import net.minecraft.particles.ParticleType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ParticleRegistry {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, "skill");
    public static RegistryObject<ParticleType<SkillsParticleData>> skillParticle = PARTICLE_TYPES.register("skill_particle", SkillsParticleType::new);

}