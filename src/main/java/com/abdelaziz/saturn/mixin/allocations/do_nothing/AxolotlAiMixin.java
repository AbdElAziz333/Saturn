package com.abdelaziz.saturn.mixin.allocations.do_nothing;

import com.abdelaziz.saturn.common.util.constants.DoNothingConstants;
import net.minecraft.world.entity.ai.behavior.DoNothing;
import net.minecraft.world.entity.animal.axolotl.AxolotlAi;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AxolotlAi.class)
public class AxolotlAiMixin {
    @Redirect(
            method = "initIdleActivity",
            at = @At(
                    value = "NEW",
                    ordinal = 0,
                    target = "(II)Lnet/minecraft/world/entity/ai/behavior/DoNothing;"
            )
    )
    private static DoNothing useStaticFinalReference(int minDuration, int maxDuration) {
        return DoNothingConstants.DO_NOTHING;
    }

    @Redirect(
            method = "initIdleActivity",
            at = @At(
                    value = "NEW",
                    ordinal = 1,
                    target = "(II)Lnet/minecraft/world/entity/ai/behavior/DoNothing;"
            )
    )
    private static DoNothing useStaticFinalReference2(int minDuration, int maxDuration) {
        return DoNothingConstants.AXOLOTL_DO_NOTHING;
    }

}