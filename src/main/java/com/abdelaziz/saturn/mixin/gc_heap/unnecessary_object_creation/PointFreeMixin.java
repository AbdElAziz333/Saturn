package com.abdelaziz.saturn.mixin.gc_heap.unnecessary_object_creation;

import com.mojang.datafixers.functions.PointFree;
import com.mojang.serialization.DynamicOps;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;
import java.util.function.Function;

@Mixin(PointFree.class)
public abstract class PointFreeMixin<T> {
    @Shadow
    private volatile boolean initialized;

    @Shadow @Nullable
    private Function<DynamicOps<?>, T> value;

    @Shadow
    public abstract Function<DynamicOps<?>, T> eval();

    @Inject(
            method = "<init>",
            at = @At(
                    value = "TAIL"
            )
    )
    private void nullValue(CallbackInfo ci) {
        value = null;
    }

    /**
     * @reason Remove object creation.
     * @author AbdElAziz
     * */
    @Overwrite (remap = false)
    @SuppressWarnings("ConstantConditions")
    public Function<DynamicOps<?>, T> evalCached() {
        if (!initialized) {
            synchronized (this) {
                if (!initialized) {
                    initialized = true;
                }
            }
        }

        return eval();
    }
}
