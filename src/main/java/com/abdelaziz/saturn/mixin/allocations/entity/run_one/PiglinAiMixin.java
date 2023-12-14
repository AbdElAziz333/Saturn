package com.abdelaziz.saturn.mixin.allocations.entity.run_one;

import com.abdelaziz.saturn.common.util.constants.EntityConstants;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.behavior.RunOne;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(PiglinAi.class)
public class PiglinAiMixin {
    /**
     * @reason Cache to a static final reference.
     * @author AbdElAziz
     * */
    @Overwrite
    private static RunOne<LivingEntity> createIdleLookBehaviors() {
        return EntityConstants.PIGLIN1;
    }

    /**
     * @reason Cache to a static final reference.
     * @author AbdElAziz
     * */
    @Overwrite
    private static RunOne<Piglin> createIdleMovementBehaviors() {
        return EntityConstants.PIGLIN2;
    }
}
