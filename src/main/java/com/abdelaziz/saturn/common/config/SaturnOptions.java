package com.abdelaziz.saturn.common.config;

import it.unimi.dsi.fastutil.objects.ReferenceArrayList;

import java.util.List;

public class SaturnOptions {
    private static final Option OPTIMIZE_MEMORY_ALLOCATION;
    private static final Option REDUCE_GC_HEAP;
    private static final Option FIX_MEMORY_LEAKS;
    private static final Option REMOVE_THREADING_DETECTOR_LOCKS;

    private static final List<Option> options = new ReferenceArrayList<>();

    static {
        OPTIMIZE_MEMORY_ALLOCATION = createOption("optimizeMemoryAllocations",
                "(default = true) Optimizes memory allocation by caching objects to static final references.",
                "allocations",
                true);

        REDUCE_GC_HEAP = createOption("reduceGCHeap",
                "(default = true) Reduces garbage collection (GC) heap by avoid creating unnecessary objects.",
                "gc_heap",
                true);

        FIX_MEMORY_LEAKS = createOption("fixMemoryLeaks",
                "(default = true) Fixes memory leaks which takes the memory continuously.",
                "leaks",
                true);

        REMOVE_THREADING_DETECTOR_LOCKS = createOption("removeThreadingDetectorLocks",
                "(default = true) Removes duplicated threading detector locks.",
                "miscellaneous.threading_detector",
                true);
    }

    public static List<Option> getOptions() {
        return options;
    }

    public static Option createOption(String name, String description, String mixinClassPath, boolean value) {
        Option option = new Option(name, description, mixinClassPath, value);
        options.add(option);
        return option;
    }
}