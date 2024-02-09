package com.abdelaziz.saturn.mixin;

import com.abdelaziz.saturn.common.Saturn;
import com.abdelaziz.saturn.common.compat.ModCompat;
import com.abdelaziz.saturn.common.config.ConfigBuilder;
import com.abdelaziz.saturn.common.config.Option;
import com.abdelaziz.saturn.common.config.SaturnOptions;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public class SaturnMixinPlugin implements IMixinConfigPlugin {
    public static final String MIXIN_PATH = "com.abdelaziz.saturn.mixin.";

    @Override
    public void onLoad(String mixinPackage) {
        ConfigBuilder.build();
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        for (Option option : SaturnOptions.getOptions()) {
            if (ConfigBuilder.getFileConfig().get(option.name()).toString().equals("false") && mixinClassName.startsWith(MIXIN_PATH + option.mixinClassPath())) {
                Saturn.getLogger().debug("Mixin " + mixinClassName + " is disabled by the config!");
                return false;
            }
        }

        return ModCompat.applyModCompatibilities(mixinClassName);
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {

    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }
}