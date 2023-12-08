package com.abdelaziz.saturn.mixin.gc_heap.unnecessary_object_creation;

import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.world.item.alchemy.Potion;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

@Mixin(ItemPredicate.class)
public class ItemPredicateMixin {
    @Shadow @Final @Mutable @Nullable
    private Potion potion;

    @Inject(
            method = "<init>(Lnet/minecraft/tags/TagKey;Ljava/util/Set;Lnet/minecraft/advancements/critereon/MinMaxBounds$Ints;Lnet/minecraft/advancements/critereon/MinMaxBounds$Ints;[Lnet/minecraft/advancements/critereon/EnchantmentPredicate;[Lnet/minecraft/advancements/critereon/EnchantmentPredicate;Lnet/minecraft/world/item/alchemy/Potion;Lnet/minecraft/advancements/critereon/NbtPredicate;)V",
            at = @At(
                    value = "TAIL"
            )
    )
    private void nullValues(CallbackInfo ci) {
        this.potion = null;
    }
}
