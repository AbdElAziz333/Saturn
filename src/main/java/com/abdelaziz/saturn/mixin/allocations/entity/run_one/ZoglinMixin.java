package com.abdelaziz.saturn.mixin.allocations.entity.run_one;

import com.abdelaziz.saturn.common.util.constants.EntityConstants;
import net.minecraft.world.entity.ai.behavior.RunOne;
import net.minecraft.world.entity.monster.Zoglin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

@Mixin(Zoglin.class)
public class ZoglinMixin {
    @Redirect(
            method = "initIdleActivity",
            at = @At(
                    value = "NEW",
                    target = "(Ljava/util/List;)Lnet/minecraft/world/entity/ai/behavior/RunOne;"
            )
    )
    private static RunOne<?> useStaticFinalReference(List<?> list) {
        return EntityConstants.ZOGLIN;
    }
}
