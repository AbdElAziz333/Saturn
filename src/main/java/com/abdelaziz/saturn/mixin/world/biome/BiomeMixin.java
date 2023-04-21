package com.abdelaziz.saturn.mixin.world.biome;

import it.unimi.dsi.fastutil.longs.Long2FloatLinkedOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Supplier;

@Mixin(Biome.class)
public abstract class BiomeMixin {

    @Mutable
    @Shadow
    @Final
    private static int TEMPERATURE_CACHE_SIZE;

    @Shadow
    protected abstract float getHeightAdjustedTemperature(BlockPos pos);

    @Inject(
            method = "<init>",
            at = @At(
                    value = "RETURN"
            )
    )
    private void removeTemperatureCache(Biome.ClimateSettings climateSettings, BiomeSpecialEffects effects, BiomeGenerationSettings generationSettings, MobSpawnSettings spawnSettings, CallbackInfo ci) {
        //remove the temperature cache size value as it unused
        TEMPERATURE_CACHE_SIZE = 0;
    }

    /**
     * @reason make temperatureCache null, saves the value memory
     * @author AbdElAziz
     * */
    @Redirect(
            method = "<init>",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/lang/ThreadLocal;withInitial(Ljava/util/function/Supplier;)Ljava/lang/ThreadLocal;"
            )
    )
    private ThreadLocal<Long2FloatLinkedOpenHashMap> nullThreadLocal(Supplier<?> supplier) {
        return null;
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