package com.abdelaziz.saturn.common.config;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class SaturnConfig {
    public static final String SATURN_GITHUB_ISSUE_TRACKER = "https://github.com/AbdElAziz333/Saturn/issues";

    public static class SaturnCommon {
        //Memory Leak Fixes
        public final ForgeConfigSpec.BooleanValue BIOME_TEMPERATURE_CACHE_LEAK_FIX;

        //Reduce Garbage Collection (GC) Heap Optimizations
        public final ForgeConfigSpec.BooleanValue UNNECESSARY_OBJECT_CREATION;

        //Miscellaneous
        public final ForgeConfigSpec.BooleanValue THREADING_DETECTOR_LOCK;

        SaturnCommon(ForgeConfigSpec.Builder builder) {
            builder.comment("This is the config file of Saturn\nTo get the best performance, i'd recommended to set all the options to true, \nuntil you got an issue or compatibility with other mod,\\nIf you do, please report the issue in the issue tracker from the link linked below:\n" + SATURN_GITHUB_ISSUE_TRACKER);
            builder.push("Memory Leak Fixes");

            BIOME_TEMPERATURE_CACHE_LEAK_FIX =
                    builder.comment("Set this to true to enable biome temperature cache leak fix.")
                            .define("biomeTemperatureCacheLeakFix", true);

            builder.pop();
            builder.push("Reduce Garbage Collection (GC) Heap Optimizations");

            UNNECESSARY_OBJECT_CREATION = builder.comment("Avoid unnecessary object creation")
                    .define("avoidUnnecessaryObjectCreation", true);

            builder.pop();
            builder.push("Miscellaneous");

            THREADING_DETECTOR_LOCK = builder.comment("Disable threading detector lock duplication")
                    .define("disableThreadingDetectorLock", true);

            builder.pop();
        }
    }

    public static final ForgeConfigSpec SPEC;
    public static final SaturnCommon COMMON;

    static {
        final Pair<SaturnCommon, ForgeConfigSpec> pair = new ForgeConfigSpec.Builder().configure(SaturnCommon::new);
        SPEC = pair.getRight();
        COMMON = pair.getLeft();
    }
}