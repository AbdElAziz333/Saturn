package com.abdelaziz.saturn.mixin.allocations.do_nothing;

import com.abdelaziz.saturn.common.util.constants.DoNothingConstants;
import net.minecraft.world.entity.ai.behavior.DoNothing;
import net.minecraft.world.entity.animal.frog.FrogAi;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(FrogAi.class)
public class FrogAiMixin {
    @Redirect(
            method = "initSwimActivity",
            at = @At(
                    value = "NEW",
                    target = "(II)Lnet/minecraft/world/entity/ai/behavior/DoNothing;"
            )
    )
    private static DoNothing useStaticFinalReference(int minDuration, int maxDuration) {
        return DoNothingConstants.DO_NOTHING;
    }

    @Redirect(
            method = {
                    "initIdleActivity",
                    "initLaySpawnActivity"
            },
            at = @At(
                    value = "NEW",
                    target = "(II)Lnet/minecraft/world/entity/ai/behavior/DoNothing;"
            )
    )
    private static DoNothing useStaticFinalReference2(int minDuration, int maxDuration) {
        return DoNothingConstants.FROG_DO_NOTHING;
    }
}
