package com.abdelaziz.saturn.mixin.allocations.vec2;

import net.minecraft.world.phys.Vec2;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Vec2.class)
public class Vec2Mixin {
    @Shadow @Final @Mutable public static Vec2 ONE;

    @Shadow @Final @Mutable public static Vec2 UNIT_X;
    @Shadow @Final @Mutable public static Vec2 UNIT_Y;

    @Shadow @Final @Mutable public static Vec2 NEG_UNIT_X;
    @Shadow @Final @Mutable public static Vec2 NEG_UNIT_Y;

    @Shadow @Final @Mutable public static Vec2 MAX;
    @Shadow @Final @Mutable public static Vec2 MIN;

    @Inject(
            method = "<init>",
            at = @At(
                    value = "TAIL"
            )
    )
    private void init(CallbackInfo callbackInfo) {
        ONE = null;
        UNIT_X = null;
        UNIT_Y = null;
        NEG_UNIT_X = null;
        NEG_UNIT_Y = null;
        MAX = null;
        MIN = null;
    }
}
