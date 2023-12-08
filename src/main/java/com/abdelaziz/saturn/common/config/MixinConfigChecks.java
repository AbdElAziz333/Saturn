package com.abdelaziz.saturn.common.config;

import net.minecraftforge.fml.loading.LoadingModList;

public class MixinConfigChecks {
    public static final String MIXIN_PATH = "com.abdelaziz.saturn.mixin.";

    public static boolean applyChecks(String mixinClassName) {
        return checkModCompatibilities(mixinClassName) || checkConfigurations(mixinClassName);
    }

    public static boolean checkModCompatibilities(String mixinClassName) {
        if (LoadingModList.get().getModFileById("memoryleakfix") != null &&
                mixinClassName.startsWith(MIXIN_PATH + "leaks.clear_memories") &&
                mixinClassName.startsWith(MIXIN_PATH + "leaks.weak_interner") &&
                mixinClassName.startsWith(MIXIN_PATH + "leaks.read_resource") &&
                mixinClassName.startsWith(MIXIN_PATH + "leaks.biome_temperature_cache")) {
            return false;
        }

        if (LoadingModList.get().getModFileById("canary") != null &&
                LoadingModList.get().getModFileById("radium") != null &&
                mixinClassName.startsWith(MIXIN_PATH + "world.threading_detector")) {
            return false;
        }

        return true;
    }

    public static boolean checkConfigurations(String mixinClassName) {
        if (MixinConfigChecks.isBiomeTemperatureCacheLeakFixEnabled(mixinClassName, !SaturnConfig.COMMON.BIOME_TEMPERATURE_CACHE_LEAK_FIX.get())) {
            return false;
        }

        if (MixinConfigChecks.isClearMemoriesLeakFixEnabled(mixinClassName, !SaturnConfig.COMMON.CLEAR_MEMORIES_LEAK_FIX.get())) {
            return false;
        }

        if (MixinConfigChecks.isReadResourceLeakFixEnabled(mixinClassName, !SaturnConfig.COMMON.READ_RESOURCE_LEAK_FIX.get())) {
            return false;
        }

        if (MixinConfigChecks.isTickingTrackerLeakFixEnabled(mixinClassName, !SaturnConfig.COMMON.TICKING_TRACKER_LEAK_FIX.get())) {
            return false;
        }

        if (MixinConfigChecks.isWeakInternerLeakFixEnabled(mixinClassName, !SaturnConfig.COMMON.WEAK_INTERNER_LEAK_FIX.get())) {
            return false;
        }

        if (MixinConfigChecks.isForgeEventsEnabled(mixinClassName, !SaturnConfig.COMMON.UNNECESSARY_FORGE_EVENTS.get())) {
            return false;
        }

        if (MixinConfigChecks.isUnnecessaryObjectCreationEnabled(mixinClassName, !SaturnConfig.COMMON.UNNECESSARY_OBJECT_CREATION.get())) {
            return false;
        }

        if (MixinConfigChecks.isThreadingDetectorLockEnabled(mixinClassName, !SaturnConfig.COMMON.THREADING_DETECTOR_LOCK.get())) {
            return false;
        }

        return true;
    }

    public static boolean isBiomeTemperatureCacheLeakFixEnabled(String mixinClassName, boolean value) {
        if (mixinClassName.startsWith(MIXIN_PATH + "leak_fix.biome_temperature_cache") && !value) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isClearMemoriesLeakFixEnabled(String mixinClassName, boolean value) {
        if (mixinClassName.startsWith(MIXIN_PATH + "leak_fix.clear_memories") && !value) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isReadResourceLeakFixEnabled(String mixinClassName, boolean value) {
        if (mixinClassName.startsWith(MIXIN_PATH + "leak_fix.read_resource") && !value) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isTickingTrackerLeakFixEnabled(String mixinClassName, boolean value) {
        if (mixinClassName.startsWith(MIXIN_PATH + "leak_fix.ticking_tracker") && !value) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isWeakInternerLeakFixEnabled(String mixinClassName, boolean value) {
        if (mixinClassName.startsWith(MIXIN_PATH + "leak_fix.weak_interner") && !value) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isForgeEventsEnabled(String mixinClassName, boolean value) {
        if (mixinClassName.startsWith(MIXIN_PATH + "gc_heap.forge_events") && !value) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isUnnecessaryObjectCreationEnabled(String mixinClassName, boolean value) {
        if (mixinClassName.startsWith(MIXIN_PATH + "gc_heap.unnecessary_object_creation") && !value) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isThreadingDetectorLockEnabled(String mixinClassName, boolean value) {
        if (mixinClassName.startsWith(MIXIN_PATH + "miscellaneous.threading_detector") && !value) {
            return false;
        } else {
            return true;
        }
    }
}
