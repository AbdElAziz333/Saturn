package com.abdelaziz.saturn.mixin.world.biome;

import com.abdelaziz.saturn.common.world.biome.SaturnTemperatureCache;
import it.unimi.dsi.fastutil.longs.Long2FloatLinkedOpenHashMap;
import net.minecraft.world.level.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.function.Supplier;

@Mixin(Biome.class)
public class BiomeMixin {

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