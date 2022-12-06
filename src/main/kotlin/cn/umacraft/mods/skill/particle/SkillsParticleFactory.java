package cn.umacraft.mods.skill.particle;

import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.world.ClientWorld;;

import javax.annotation.Nullable;
public class SkillsParticleFactory implements IParticleFactory<SkillsParticleData> {
    private final IAnimatedSprite sprites;

    public SkillsParticleFactory(IAnimatedSprite sprites) {
        this.sprites = sprites;
    }

    @Nullable
    @Override
    public Particle makeParticle(SkillsParticleData data, ClientWorld world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        SkillsParticle particle = new SkillsParticle(world, x, y, z, data.getSpeed(), data.getColor(), data.getDiameter());
        //particle.selectSpriteRandomly(sprites); 因为目前还没做材质
        return particle;
    }
}
