package com.abdelaziz.saturn.mixin.allocations.entity.run_one;

import com.abdelaziz.saturn.common.util.constants.EntityConstants;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.behavior.RunOne;
import net.minecraft.world.entity.animal.allay.AllayAi;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

@Mixin(AllayAi.class)
public class AllayAiMixin {
    @Redirect(
            method = "initIdleActivity",
            at = @At(
                    value = "NEW",
                    target = "(Ljava/util/List;)Lnet/minecraft/world/entity/ai/behavior/RunOne;"
            )
    )
    private static RunOne<PathfinderMob> useStaticFinalReference(List<?> list) {
        return EntityConstants.ALLAY;
    }
}
