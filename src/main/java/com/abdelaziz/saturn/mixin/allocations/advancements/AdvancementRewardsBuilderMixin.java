package com.abdelaziz.saturn.mixin.allocations.advancements;

import com.abdelaziz.saturn.common.util.constants.AdvancementConstants;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.commands.CommandFunction;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import javax.annotation.Nullable;
import java.util.List;

@Mixin(AdvancementRewards.Builder.class)
public abstract class AdvancementRewardsBuilderMixin {
    @Shadow
    private int experience;

    @Shadow @Final
    private List<ResourceLocation> loot;

    @Shadow @Final
    private List<ResourceLocation> recipes;

    @Shadow @Nullable
    private ResourceLocation function;

    /**
     * @reason Cache ResourceLocation array creation.
     * @author AbdElAziz
     * */
    @Overwrite
    public AdvancementRewards build() {
        return new AdvancementRewards(this.experience,
                this.loot.toArray(AdvancementConstants.CACHED_RESOURCE_LOCATION),
                this.recipes.toArray(AdvancementConstants.CACHED_RESOURCE_LOCATION),
                this.function == null ?
                        CommandFunction.CacheableFunction.NONE :
                        new CommandFunction.CacheableFunction(this.function));
    }
}
