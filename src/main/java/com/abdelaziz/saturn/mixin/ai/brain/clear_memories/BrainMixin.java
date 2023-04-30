package com.abdelaziz.saturn.mixin.ai.brain.clear_memories;

import com.abdelaziz.saturn.common.ai.brain.ClearableMemory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.memory.ExpirableValue;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Map;
import java.util.Optional;

@Mixin(Brain.class)
public abstract class BrainMixin<E extends LivingEntity> implements ClearableMemory {

    @Shadow
    @Final
    private Map<MemoryModuleType<?>, Optional<? extends ExpirableValue<?>>> memories;

    @Override
    public void clearMemories() {
        this.memories.keySet().forEach((p) -> {
            this.memories.put(p, Optional.empty());
        });
    }
}
