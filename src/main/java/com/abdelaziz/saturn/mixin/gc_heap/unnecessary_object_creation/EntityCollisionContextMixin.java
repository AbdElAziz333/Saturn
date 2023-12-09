package com.abdelaziz.saturn.mixin.gc_heap.unnecessary_object_creation;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

@Mixin(EntityCollisionContext.class)
public class EntityCollisionContextMixin {
    @Shadow @Final @Mutable @Nullable
    private Entity entity;

    @Inject(
            method = "<init>(ZDLnet/minecraft/world/item/ItemStack;Ljava/util/function/Predicate;Lnet/minecraft/world/entity/Entity;)V",
            at = @At(
                    value = "TAIL"
            )
    )
    private void initNull(CallbackInfo ci) {
        this.entity = null;
    }
}
