package com.abdelaziz.saturn.mixin.gc_heap.unnecessary_object_creation;

import net.minecraft.advancements.critereon.EntityPredicate;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

@Mixin(EntityPredicate.class)
public class EntityPredicateMixin {
    @Shadow @Final @Mutable @Nullable
    private String team;

    @Inject(
            method = {
                    "<init>(Lnet/minecraft/advancements/critereon/EntityTypePredicate;Lnet/minecraft/advancements/critereon/DistancePredicate;Lnet/minecraft/advancements/critereon/LocationPredicate;Lnet/minecraft/advancements/critereon/LocationPredicate;Lnet/minecraft/advancements/critereon/MobEffectsPredicate;Lnet/minecraft/advancements/critereon/NbtPredicate;Lnet/minecraft/advancements/critereon/EntityFlagsPredicate;Lnet/minecraft/advancements/critereon/EntityEquipmentPredicate;Lnet/minecraft/advancements/critereon/EntitySubPredicate;Ljava/lang/String;)V",
                    "<init>(Lnet/minecraft/advancements/critereon/EntityTypePredicate;Lnet/minecraft/advancements/critereon/DistancePredicate;Lnet/minecraft/advancements/critereon/LocationPredicate;Lnet/minecraft/advancements/critereon/LocationPredicate;Lnet/minecraft/advancements/critereon/MobEffectsPredicate;Lnet/minecraft/advancements/critereon/NbtPredicate;Lnet/minecraft/advancements/critereon/EntityFlagsPredicate;Lnet/minecraft/advancements/critereon/EntityEquipmentPredicate;Lnet/minecraft/advancements/critereon/EntitySubPredicate;Lnet/minecraft/advancements/critereon/EntityPredicate;Lnet/minecraft/advancements/critereon/EntityPredicate;Lnet/minecraft/advancements/critereon/EntityPredicate;Ljava/lang/String;)V"
            },
            at = @At(
                    value = "TAIL"
            )
    )
    private void initNull(CallbackInfo ci) {
        this.team = null;
    }
}
