package com.abdelaziz.saturn.common.world.biome;

import it.unimi.dsi.fastutil.longs.Long2FloatLinkedOpenHashMap;
import net.minecraft.util.Util;

public class SaturnTemperatureCache {
    public static final ThreadLocal<Long2FloatLinkedOpenHashMap> saturnTemperatureCache = ThreadLocal.withInitial(() ->
            Util.make(() -> {
                Long2FloatLinkedOpenHashMap long2FloatLinkedOpenHashMap = new Long2FloatLinkedOpenHashMap(
                        1024, 0.25F
                ) {
                    @Override protected void rehash(int n) {}
                };
                long2FloatLinkedOpenHashMap.defaultReturnValue(Float.NaN);
                return long2FloatLinkedOpenHashMap;
            })
    );
}
