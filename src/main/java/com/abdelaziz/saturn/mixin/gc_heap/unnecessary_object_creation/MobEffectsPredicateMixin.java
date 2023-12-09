package com.abdelaziz.saturn.mixin.gc_heap.unnecessary_object_creation;

import net.minecraft.advancements.critereon.MobEffectsPredicate;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

@Mixin(MobEffectsPredicate.MobEffectInstancePredicate.class)
public abstract class MobEffectsPredicateMixin {
    @Shadow @Final @Mutable @Nullable
    private Boolean ambient;

    @Shadow @Final @Mutable @Nullable
    private Boolean visible;

    @Inject(
            method = "<init>(Lnet/minecraft/advancements/critereon/MinMaxBounds$Ints;Lnet/minecraft/advancements/critereon/MinMaxBounds$Ints;Ljava/lang/Boolean;Ljava/lang/Boolean;)V",
            at = @At(
                    value = "TAIL"
            )
    )
    private void initNull(CallbackInfo ci) {
        this.ambient = null;
        this.visible = null;
    }
}
