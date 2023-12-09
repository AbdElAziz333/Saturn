package com.abdelaziz.saturn.mixin.gc_heap.unnecessary_object_creation;

import net.minecraft.advancements.critereon.EntityFlagsPredicate;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

@Mixin(EntityFlagsPredicate.class)
public class EntityFlagsPredicateMixin {
    @Shadow @Final @Mutable @Nullable
    private Boolean isCrouching;

    @Shadow @Final @Mutable @Nullable
    private Boolean isSprinting;

    @Shadow @Final @Mutable @Nullable
    private Boolean isSwimming;

    @Shadow @Final @Mutable @Nullable
    private Boolean isBaby;

    @Inject(
            method = "<init>",
            at = @At(
                    value = "TAIL"
            )
    )
    private void nullValues(CallbackInfo ci) {
        this.isCrouching = null;
        this.isSprinting = null;
        this.isSwimming = null;
        this.isBaby = null;
    }
}
