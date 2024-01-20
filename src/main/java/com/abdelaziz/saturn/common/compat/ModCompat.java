package com.abdelaziz.saturn.common.compat;

import com.abdelaziz.saturn.common.Saturn;
import net.minecraftforge.fml.loading.FMLLoader;

public class ModCompat {
    public static final String MIXIN_PATH = "com.abdelaziz.saturn.mixin.";

    public static boolean applyModCompatibilities(String mixinClassName) {
        if (FMLLoader.getLoadingModList().getModFileById("memoryleakfix") != null && (
                        mixinClassName.startsWith(MIXIN_PATH + "leaks.biome_temperature_cache") ||
                        mixinClassName.startsWith(MIXIN_PATH + "leaks.clear_memories") ||
                        mixinClassName.startsWith(MIXIN_PATH + "leaks.read_resource")
        )) {
            Saturn.getLogger().info("Memory Leak Fix mod is loaded, Saturn will disable some of its optimizations, but recommended to remove memory leak fix.");
            return false;
        }

        if (FMLLoader.getLoadingModList().getModFileById("radon") != null && mixinClassName.startsWith(MIXIN_PATH + "leaks.ticking_tracker")) {
            Saturn.getLogger().info("Radon mod is loaded, Saturn will disable the ticking tracker memory leak fix.");
            return false;
        }

        if (FMLLoader.getLoadingModList().getModFileById("radium") != null && (
                        mixinClassName.startsWith(MIXIN_PATH + "miscellaneous.threading_detector") ||
                        mixinClassName.startsWith(MIXIN_PATH + "leaks.biome_temperature_cache") ||
                        mixinClassName.startsWith(MIXIN_PATH + "allocations.composter")
        )) {
            Saturn.getLogger().info("Radium mod is loaded, Saturn will disable 1 memory leak fix and 2 optimizations.");
            return false;
        }

        return true;
    }
}
