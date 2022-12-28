package com.abdelaziz.saturn.mixin.world.biome;

import it.unimi.dsi.fastutil.longs.Long2FloatLinkedOpenHashMap;
import net.minecraft.Util;
import net.minecraft.world.level.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.function.Supplier;

@Mixin(Biome.class)
public class BiomeMixin {

    private static final ThreadLocal<Long2FloatLinkedOpenHashMap> saturnTemperatureCache = ThreadLocal.withInitial(() ->
            Util.make(() -> {
                Long2FloatLinkedOpenHashMap long2FloatLinkedOpenHashMap = new Long2FloatLinkedOpenHashMap(
                        1024, 0.25F
                ) {
                    @Override
                    protected void rehash(int n) {
                    }
                };
                long2FloatLinkedOpenHashMap.defaultReturnValue(Float.NaN);
                return long2FloatLinkedOpenHashMap;
            })
    );

    @Redirect(
            method = "<init>",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/lang/ThreadLocal;withInitial(Ljava/util/function/Supplier;)Ljava/lang/ThreadLocal;"
            )
    )
    private ThreadLocal<Long2FloatLinkedOpenHashMap> staticThreadLocal(Supplier<?> supplier) {
        return SaturnTemperatureCache.saturnTemperatureCache;
    }
}