package com.abdelaziz.saturn.mixin.allocations.fall_sounds;

import com.abdelaziz.saturn.common.util.constants.FallSoundConstants;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.decoration.ArmorStand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(ArmorStand.class)
public class ArmorStandMixin {
    /**
     * @reason Cache to a static final reference.
     * @author AbdElAziz
     * */
    @Overwrite
    public LivingEntity.Fallsounds getFallSounds() {
        return FallSoundConstants.ARMOR_STAND;
    }
}
