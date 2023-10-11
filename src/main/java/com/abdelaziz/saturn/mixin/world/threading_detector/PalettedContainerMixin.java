package com.abdelaziz.saturn.mixin.world.threading_detector;

import net.minecraft.util.ThreadingDetector;
import net.minecraft.world.level.chunk.PalettedContainer;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Profiler shows ThreadingDetector is duplicated so much.
 * */
@Mixin(PalettedContainer.class)
public class PalettedContainerMixin {
    @Shadow @Final @Mutable
    private ThreadingDetector threadingDetector;

    @Inject(
            method = {
                    "<init>(Lnet/minecraft/core/IdMap;Ljava/lang/Object;Lnet/minecraft/world/level/chunk/PalettedContainer$Strategy;)V",
                    "<init>(Lnet/minecraft/core/IdMap;Lnet/minecraft/world/level/chunk/PalettedContainer$Strategy;Lnet/minecraft/world/level/chunk/PalettedContainer$Data;)V",
                    "<init>(Lnet/minecraft/core/IdMap;Lnet/minecraft/world/level/chunk/PalettedContainer$Strategy;Lnet/minecraft/world/level/chunk/PalettedContainer$Configuration;Lnet/minecraft/util/BitStorage;Ljava/util/List;)V"
            },
            at = @At(
                    value = "TAIL"
            )
    )
    private void init(CallbackInfo ci) {
        this.threadingDetector = null;
    }

    /**
     * @reason remove threading detector.
     * @author AbdElAziz
     * */
    @Overwrite
    public void acquire() {}

    /**
     * @reason remove threading detector.
     * @author AbdElAziz
     * */
    @Overwrite
    public void release() {}
}
