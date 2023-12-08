package com.abdelaziz.saturn.mixin.gc_heap.unnecessary_object_creation;

import net.minecraft.core.BlockPos;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.event.level.LevelEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(LevelEvent.PotentialSpawns.class)
public class PotentialSpawnsMixin {
    @Shadow @Final @Mutable
    private MobCategory mobcategory;

    @Shadow @Final @Mutable
    private BlockPos pos;

    @Shadow @Final @Mutable
    private List<MobSpawnSettings.SpawnerData> list;

    @Inject(
            remap = false,
            method = "<init>",
            at = @At(
                    value = "TAIL"
            )
    )
    private void nullValue(LevelAccessor level, MobCategory category, BlockPos pos, WeightedRandomList<?> oldList, CallbackInfo ci) {
        this.mobcategory = null;
        this.pos = null;
        this.list = null;
    }
}
