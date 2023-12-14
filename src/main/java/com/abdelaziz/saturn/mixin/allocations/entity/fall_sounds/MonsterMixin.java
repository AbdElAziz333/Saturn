package com.abdelaziz.saturn.mixin.allocations.entity.fall_sounds;

import com.abdelaziz.saturn.common.util.constants.EntityConstants;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(Monster.class)
public class MonsterMixin {
    /**
     * @reason Cache to a static final reference.
     * @author AbdElAziz
     * */
    @Overwrite
    public LivingEntity.Fallsounds getFallSounds() {
        return EntityConstants.MONSTER;
    }
}
