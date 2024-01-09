package com.abdelaziz.saturn.common.util.constants;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.behavior.DoNothing;
import net.minecraft.world.item.ItemCooldowns;

public interface EntityConstants {
    ItemCooldowns ITEM_COOLDOWNS = new ItemCooldowns();
    float[] WHITE_SHEEP_COLOR = new float[]{0.9019608F, 0.9019608F, 0.9019608F};

    //Fall Sounds
    LivingEntity.Fallsounds LIVING_ENTITY = new LivingEntity.Fallsounds(SoundEvents.GENERIC_SMALL_FALL, SoundEvents.GENERIC_BIG_FALL);
    LivingEntity.Fallsounds PLAYER = new LivingEntity.Fallsounds(SoundEvents.PLAYER_SMALL_FALL, SoundEvents.PLAYER_BIG_FALL);
    LivingEntity.Fallsounds MONSTER = new LivingEntity.Fallsounds(SoundEvents.HOSTILE_SMALL_FALL, SoundEvents.HOSTILE_BIG_FALL);
    LivingEntity.Fallsounds ARMOR_STAND = new LivingEntity.Fallsounds(SoundEvents.ARMOR_STAND_FALL, SoundEvents.ARMOR_STAND_FALL);

    //DoNothing
    DoNothing CACHED_DO_NOTHING = new DoNothing(30, 60);
}
