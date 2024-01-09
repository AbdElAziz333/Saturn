package com.abdelaziz.saturn.mixin.allocations.do_nothing;

import com.abdelaziz.saturn.common.util.constants.DoNothingConstants;
import net.minecraft.world.entity.ai.behavior.DoNothing;
import net.minecraft.world.entity.animal.goat.GoatAi;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GoatAi.class)
public class GoatAiMixin {
    @Redirect(
            method = "initIdleActivity",
            at = @At(
                    value = "NEW",
                    target = "(II)Lnet/minecraft/world/entity/ai/behavior/DoNothing;"
            )
    )
    private static DoNothing useStaticFinalReference(int minDuration, int maxDuration) {
        return DoNothingConstants.DO_NOTHING;
    }
}
