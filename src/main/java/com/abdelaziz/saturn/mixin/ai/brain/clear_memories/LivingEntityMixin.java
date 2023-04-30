package com.abdelaziz.saturn.mixin.ai.brain.clear_memories;

import com.abdelaziz.saturn.common.ai.brain.ClearableMemory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements ClearableMemory {
    @Shadow
    protected Brain<?> brain;

    public LivingEntityMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public void remove(Entity.RemovalReason removalReason) {
        super.remove(removalReason);
        ((ClearableMemory)this.brain).clearMemories();
    }
}
