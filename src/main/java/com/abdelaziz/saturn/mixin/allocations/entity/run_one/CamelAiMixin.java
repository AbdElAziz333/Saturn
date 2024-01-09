package com.abdelaziz.saturn.mixin.allocations.entity.run_one;

import com.abdelaziz.saturn.common.util.constants.EntityConstants;
import net.minecraft.world.entity.ai.behavior.RunOne;
import net.minecraft.world.entity.animal.camel.CamelAi;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;
import java.util.Map;

@Mixin(CamelAi.class)
public class CamelAiMixin {
    @Redirect(
            method = "initIdleActivity",
            at =  @At(
                    value = "NEW",
                    target = "(Ljava/util/Map;Ljava/util/List;)Lnet/minecraft/world/entity/ai/behavior/RunOne;"
            )
    )
    private static RunOne<?> useStaticFinalReference(Map<?, ?> map, List<?> list) {
        return EntityConstants.CAMEL;
    }
}