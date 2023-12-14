package com.abdelaziz.saturn.common.util.constants;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Pair;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.behavior.*;
import net.minecraft.world.entity.ai.behavior.declarative.BehaviorBuilder;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.animal.camel.Camel;
import net.minecraft.world.entity.animal.camel.CamelAi;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.entity.animal.sniffer.Sniffer;
import net.minecraft.world.entity.animal.sniffer.SnifferAi;
import net.minecraft.world.entity.monster.Zoglin;
import net.minecraft.world.entity.monster.hoglin.Hoglin;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.item.ItemCooldowns;

import java.util.function.Predicate;

public interface EntityConstants {
    ItemCooldowns ITEM_COOLDOWNS = new ItemCooldowns();
    float[] WHITE_SHEEP_COLOR = new float[]{0.9019608F, 0.9019608F, 0.9019608F};

    //Fall Sounds
    LivingEntity.Fallsounds LIVING_ENTITY = new LivingEntity.Fallsounds(SoundEvents.GENERIC_SMALL_FALL, SoundEvents.GENERIC_BIG_FALL);
    LivingEntity.Fallsounds PLAYER = new LivingEntity.Fallsounds(SoundEvents.PLAYER_SMALL_FALL, SoundEvents.PLAYER_BIG_FALL);
    LivingEntity.Fallsounds MONSTER = new LivingEntity.Fallsounds(SoundEvents.HOSTILE_SMALL_FALL, SoundEvents.HOSTILE_BIG_FALL);
    LivingEntity.Fallsounds ARMOR_STAND = new LivingEntity.Fallsounds(SoundEvents.ARMOR_STAND_FALL, SoundEvents.ARMOR_STAND_FALL);

    //RunOne
    DoNothing DO_NOTHING = new DoNothing(30, 60);
    DoNothing SNIFFER_DO_NOTHING = new DoNothing(5, 20);

    RunOne<PathfinderMob> ALLAY = new RunOne<>(ImmutableList.of(Pair.of(RandomStroll.fly(1.0F), 2), Pair.of(SetWalkTargetFromLookTarget.create(1.0F, 3), 2), Pair.of(DO_NOTHING, 1)));
    RunOne<Camel> CAMEL = new RunOne<>(ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT), ImmutableList.of(Pair.of(BehaviorBuilder.triggerIf(Predicate.not(Camel::refuseToMove), RandomStroll.stroll(2.0F)), 1), Pair.of(BehaviorBuilder.triggerIf(Predicate.not(Camel::refuseToMove), SetWalkTargetFromLookTarget.create(2.0F, 3)), 1), Pair.of(new CamelAi.RandomSitting(20), 1), Pair.of(DO_NOTHING, 1)));
    RunOne<Frog> FROG = new RunOne<>(ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT), ImmutableList.of(Pair.of(RandomStroll.stroll(1.0F), 1), Pair.of(SetWalkTargetFromLookTarget.create(1.0F, 3), 1), Pair.of(new Croak(), 3), Pair.of(BehaviorBuilder.triggerIf(Entity::onGround), 2)));
    RunOne<Goat> GOAT = new RunOne<>(ImmutableList.of(Pair.of(RandomStroll.stroll(1.0F), 2), Pair.of(SetWalkTargetFromLookTarget.create(1.0F, 3), 2), Pair.of(DO_NOTHING, 1)));
    RunOne<Hoglin> HOGLIN = new RunOne<>(ImmutableList.of(Pair.of(RandomStroll.stroll(0.4F), 2), Pair.of(SetWalkTargetFromLookTarget.create(0.4F, 3), 2), Pair.of(DO_NOTHING, 1)));
    RunOne<LivingEntity> PIGLIN1 = new RunOne<>(ImmutableList.<Pair<? extends BehaviorControl<? super LivingEntity>, Integer>>builder().addAll(PiglinAi.createLookBehaviors()).add(Pair.of(DO_NOTHING, 1)).build());
    RunOne<Piglin> PIGLIN2 = new RunOne<>(ImmutableList.of(Pair.of(RandomStroll.stroll(0.6F), 2), Pair.of(InteractWith.of(EntityType.PIGLIN, 8, MemoryModuleType.INTERACTION_TARGET, 0.6F, 2), 2), Pair.of(BehaviorBuilder.triggerIf(PiglinAi::doesntSeeAnyPlayerHoldingLovedItem, SetWalkTargetFromLookTarget.create(0.6F, 3)), 2), Pair.of(DO_NOTHING, 1)));
    RunOne<PiglinBrute> PIGLIN_BRUTE1 = new RunOne<>(ImmutableList.of(Pair.of(SetEntityLookTarget.create(EntityType.PLAYER, 8.0F), 1), Pair.of(SetEntityLookTarget.create(EntityType.PIGLIN, 8.0F), 1), Pair.of(SetEntityLookTarget.create(EntityType.PIGLIN_BRUTE, 8.0F), 1), Pair.of(SetEntityLookTarget.create(8.0F), 1), Pair.of(DO_NOTHING, 1)));
    RunOne<PiglinBrute> PIGLIN_BRUTE2 = new RunOne<>(ImmutableList.of(Pair.of(RandomStroll.stroll(0.6F), 2), Pair.of(InteractWith.of(EntityType.PIGLIN, 8, MemoryModuleType.INTERACTION_TARGET, 0.6F, 2), 2), Pair.of(InteractWith.of(EntityType.PIGLIN_BRUTE, 8, MemoryModuleType.INTERACTION_TARGET, 0.6F, 2), 2), Pair.of(StrollToPoi.create(MemoryModuleType.HOME, 0.6F, 2, 100), 2), Pair.of(StrollAroundPoi.create(MemoryModuleType.HOME, 0.6F, 5), 2), Pair.of(DO_NOTHING, 1)));
    RunOne<Sniffer> SNIFFER = new RunOne<>(ImmutableList.of(Pair.of(SetWalkTargetFromLookTarget.create(1.0F, 3), 2), Pair.of(new SnifferAi.Scenting(40, 80), 1), Pair.of(new SnifferAi.Sniffing(40, 80), 1), Pair.of(SetEntityLookTarget.create(EntityType.PLAYER, 6.0F), 1), Pair.of(RandomStroll.stroll(1.0F), 1), Pair.of(SNIFFER_DO_NOTHING, 2)));
    RunOne<LivingEntity> VILLAGER1 = new RunOne<>(ImmutableList.of(Pair.of(SetEntityLookTarget.create(EntityType.VILLAGER, 8.0F), 2), Pair.of(SetEntityLookTarget.create(EntityType.PLAYER, 8.0F), 2), Pair.of(DO_NOTHING, 8)));
    RunOne<LivingEntity> VILLAGER2 = new RunOne<>(ImmutableList.of(Pair.of(SetEntityLookTarget.create(EntityType.CAT, 8.0F), 8), Pair.of(SetEntityLookTarget.create(EntityType.VILLAGER, 8.0F), 2), Pair.of(SetEntityLookTarget.create(EntityType.PLAYER, 8.0F), 2), Pair.of(SetEntityLookTarget.create(MobCategory.CREATURE, 8.0F), 1), Pair.of(SetEntityLookTarget.create(MobCategory.WATER_CREATURE, 8.0F), 1), Pair.of(SetEntityLookTarget.create(MobCategory.AXOLOTLS, 8.0F), 1), Pair.of(SetEntityLookTarget.create(MobCategory.UNDERGROUND_WATER_CREATURE, 8.0F), 1), Pair.of(SetEntityLookTarget.create(MobCategory.WATER_AMBIENT, 8.0F), 1), Pair.of(SetEntityLookTarget.create(MobCategory.MONSTER, 8.0F), 1), Pair.of(DO_NOTHING, 2)));
    RunOne<Warden> WARDEN = new RunOne<>(ImmutableMap.of(MemoryModuleType.IS_SNIFFING, MemoryStatus.VALUE_ABSENT), ImmutableList.of(Pair.of(RandomStroll.stroll(0.5F), 2), Pair.of(DO_NOTHING, 1)));
    RunOne<Zoglin> ZOGLIN = new RunOne<>(ImmutableList.of(Pair.of(RandomStroll.stroll(0.4F), 2), Pair.of(SetWalkTargetFromLookTarget.create(0.4F, 3), 2), Pair.of(DO_NOTHING, 1)));
}
