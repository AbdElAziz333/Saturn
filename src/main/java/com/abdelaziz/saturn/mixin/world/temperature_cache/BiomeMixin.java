package com.abdelaziz.saturn.mixin.world.temperature_cache;

import it.unimi.dsi.fastutil.longs.Long2FloatLinkedOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.biome.Biome;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Biome.class)
public abstract class BiomeMixin {

    @Shadow
    protected abstract float getHeightAdjustedTemperature(BlockPos pos);

    @Shadow @Final @Mutable
    private ThreadLocal<Long2FloatLinkedOpenHashMap> temperatureCache;

    @Inject(
            method = "<init>",
            at = @At(
                    value = "TAIL"
            )
    )
    private void nullThreadLocal(CallbackInfo ci) {
        this.temperatureCache = null;
    }

    /**
     * @reason remove the vanilla temperature
     * @author AbdElAziz
     * */
    @Deprecated
    @Overwrite
    public float getTemperature(BlockPos pos) {
        return getHeightAdjustedTemperature(pos);
    }
}