package com.abdelaziz.saturn.common.util.constants;

import net.minecraft.world.entity.ai.behavior.DoNothing;

public interface DoNothingConstants {
    DoNothing DO_NOTHING = new DoNothing(30, 60);
    DoNothing VILLAGERS_DO_NOTHING = new DoNothing(20, 40);
    DoNothing AXOLOTL_DO_NOTHING = new DoNothing(200, 400);
    DoNothing PIGLIN_DO_NOTHING = new DoNothing(10, 20);
    DoNothing FROG_DO_NOTHING = new DoNothing(5, 20);
}
