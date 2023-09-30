package com.abdelaziz.saturn.mixin.miscellaneous.strong_interner;

import com.google.common.collect.Interner;
import com.google.common.collect.Interners;
import net.minecraft.tags.TagKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(TagKey.class)
public class TagKeyMixin {

    /**
     * @reason Fix memory leak in TagKey creation.
     * @author AbdElAziz
     * */
    @Redirect(
            method = "<clinit>",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/google/common/collect/Interners;newStrongInterner()Lcom/google/common/collect/Interner;"
            )
    )
    private static <E> Interner<E> useWeakInterner() {
        return Interners.newWeakInterner();
    }
}
