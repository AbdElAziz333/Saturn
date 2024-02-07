package com.abdelaziz.saturn.mixin.allocations.advancements;

import com.abdelaziz.saturn.common.util.constants.AdvancementConstants;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.commands.CommandFunction;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AdvancementRewards.class)
public class AdvancementRewardsMixin {
    @Shadow @Final @Mutable
    public static AdvancementRewards EMPTY;

    /**
     * TODO: better method name & description.
     * @reason Cache new ResourceLocation array creation.
     * @author AbdElAziz
     * @since 0.0.1
     * */
    @Inject(
            method = "<clinit>",
            at = @At(
                    value = "TAIL"
            )
    )
    private static void cacheResourceLocation(CallbackInfo ci) {
        EMPTY = new AdvancementRewards(0, AdvancementConstants.CACHED_RESOURCE_LOCATION, AdvancementConstants.CACHED_RESOURCE_LOCATION, CommandFunction.CacheableFunction.NONE);
    }
}
