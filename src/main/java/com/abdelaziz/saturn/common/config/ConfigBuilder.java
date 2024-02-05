package com.abdelaziz.saturn.common.config;

import com.abdelaziz.saturn.common.Saturn;
import com.electronwill.nightconfig.core.Config;
import com.electronwill.nightconfig.core.ConfigSpec;
import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.File;

public class ConfigBuilder {
    private static CommentedFileConfig fileConfig;
    
    public static void build() {
        File file = new File(String.valueOf(FMLPaths.CONFIGDIR.get().resolve("saturn-optimizations.toml")));
        Saturn.getLogger().info("Loaded Saturn config file with {} configurable options", SaturnOptions.getOptions().size());
        Config.setInsertionOrderPreserved(true);
        ConfigSpec config = new ConfigSpec();

        for (Option option : SaturnOptions.getOptions()) {
            config.define(option.name(), option.value());
        }

        fileConfig = CommentedFileConfig.builder(file).sync().build();
        fileConfig.load();

        for (Option option : SaturnOptions.getOptions()) {
            fileConfig.setComment(option.name(), option.description());
        }

        config.correct(fileConfig);
        fileConfig.save();
        fileConfig.close();
    }

    public static CommentedFileConfig getFileConfig() {
        return fileConfig;
    }
}