package com.abdelaziz.saturn.mixin.allocations.animal;

import com.abdelaziz.saturn.common.util.constants.SaturnConstants;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(Pig.class)
public class PigMixin {
    /**
     * @reason Cache to a static final reference.
     * @author AbdElAziz
     * */
    @Overwrite
    protected Vec3 getRiddenInput(Player player, Vec3 vec3) {
        return SaturnConstants.EMPTY_VECTOR3;
    }
}
