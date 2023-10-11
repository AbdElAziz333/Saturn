package com.abdelaziz.saturn.mixin;

import net.minecraftforge.fml.loading.LoadingModList;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public class SaturnMixinPlugin implements IMixinConfigPlugin {
    private static final String SATURN_MIXINS_PATH = "com.abdelaziz.saturn.mixin.";

    @Override
    public void onLoad(String mixinPackage) {

    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        if (LoadingModList.get().getModFileById("memoryleakfix") != null &&
                mixinClassName.startsWith(SATURN_MIXINS_PATH + "ai.brain") &&
                mixinClassName.startsWith(SATURN_MIXINS_PATH + "miscellaneous.read_resource") &&
                mixinClassName.startsWith(SATURN_MIXINS_PATH + "world.temperature_cache")) {
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
