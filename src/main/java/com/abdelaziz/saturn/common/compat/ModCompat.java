package com.abdelaziz.saturn.common.compat;

import net.minecraftforge.fml.loading.FMLLoader;

public class ModCompat {
    public static final String MIXIN_PATH = "com.abdelaziz.saturn.mixin.";

    public static boolean applyModCompatibilities(String mixinClassName) {
        if (FMLLoader.getLoadingModList().getModFileById("memoryleakfix") != null && (
                        mixinClassName.startsWith(MIXIN_PATH + "leaks.biome_temperature_cache") ||
                        mixinClassName.startsWith(MIXIN_PATH + "leaks.clear_memories") ||
                        mixinClassName.startsWith(MIXIN_PATH + "leaks.read_resource")
        )) {
            return false;
        }

        if (FMLLoader.getLoadingModList().getModFileById("radium") != null && (
                        mixinClassName.startsWith(MIXIN_PATH + "miscellaneous.threading_detector") ||
                        mixinClassName.startsWith(MIXIN_PATH + "leaks.biome_temperature_cache") ||
                        mixinClassName.startsWith(MIXIN_PATH + "allocations.composter")
        )) {
            return false;
        }

        return true;
    }
}
