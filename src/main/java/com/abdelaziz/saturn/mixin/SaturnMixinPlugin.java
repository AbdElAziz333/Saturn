package com.abdelaziz.saturn.mixin;

import net.minecraftforge.fml.loading.FMLLoader;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public class SaturnMixinPlugin implements IMixinConfigPlugin {
    public static final String MIXIN_PATH = "com.abdelaziz.saturn.mixin.";

    @Override
    public void onLoad(String mixinPackage) {
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        if (mixinClassName.startsWith(MIXIN_PATH + "leaks.biome_temperature_cache") && (FMLLoader.getLoadingModList().getModFileById("memoryleakfix") != null)) {
            return false;
        }

        if ((mixinClassName.startsWith(MIXIN_PATH + "miscellaneous.threading_detector") || mixinClassName.startsWith(MIXIN_PATH + "leaks.biome_temperature_cache")) &&
                (FMLLoader.getLoadingModList().getModFileById("canary") != null || FMLLoader.getLoadingModList().getModFileById("radium") != null)) {
            return false;
        }

        return true;
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
