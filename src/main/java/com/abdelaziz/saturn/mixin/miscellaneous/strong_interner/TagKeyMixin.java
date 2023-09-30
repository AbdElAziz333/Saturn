package com.abdelaziz.saturn.mixin.miscellaneous.strong_interner;

import com.google.common.collect.Interner;
import com.google.common.collect.Interners;
import net.minecraft.tags.TagKey;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TagKey.class)
public class TagKeyMixin {
    @Shadow @Final @Mutable
    private static Interner<TagKey<?>> VALUES;

    /**
     * @reason Fix memory leak in TagKey creation.
     * @author AbdElAziz
     * */
    @Inject(
            method = "<init>",
            at = @At(
                    value = "TAIL"
            )
    )
    private void useWeakInterner(CallbackInfo ci) {
        VALUES = Interners.newWeakInterner();
    }
}