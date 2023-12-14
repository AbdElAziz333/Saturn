package com.abdelaziz.saturn.mixin.allocations.entity.run_one;

import com.abdelaziz.saturn.common.util.constants.EntityConstants;
import net.minecraft.world.entity.ai.behavior.RunOne;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.entity.monster.piglin.PiglinBruteAi;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(PiglinBruteAi.class)
public class PiglinBruteAiMixin {
    /**
     * @reason Cache to a static final reference.
     * @author AbdElAziz
     * */
    @Overwrite
    private static RunOne<PiglinBrute> createIdleLookBehaviors() {
        return EntityConstants.PIGLIN_BRUTE1;
    }

    /**
     * @reason Cache to a static final reference.
     * @author AbdElAziz
     * */
    @Overwrite
    private static RunOne<PiglinBrute> createIdleMovementBehaviors() {
        return EntityConstants.PIGLIN_BRUTE2;
    }
}
