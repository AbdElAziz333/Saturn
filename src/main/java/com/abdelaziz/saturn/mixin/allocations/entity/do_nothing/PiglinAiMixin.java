package com.abdelaziz.saturn.mixin.allocations.entity.do_nothing;

import com.abdelaziz.saturn.common.util.constants.EntityConstants;
import net.minecraft.world.entity.ai.behavior.DoNothing;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PiglinAi.class)
public class PiglinAiMixin {
    @Redirect(
            method = {
                    "createIdleLookBehaviors",
                    "createIdleMovementBehaviors"
            },
            at = @At(
                    value = "NEW",
                    target = "(II)Lnet/minecraft/world/entity/ai/behavior/DoNothing;"
            )
    )
    private static DoNothing useStaticFinalReference(int minDuration, int maxDuration) {
        return EntityConstants.CACHED_DO_NOTHING;
    }
}
