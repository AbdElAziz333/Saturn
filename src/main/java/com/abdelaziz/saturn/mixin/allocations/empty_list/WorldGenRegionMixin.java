package com.abdelaziz.saturn.mixin.allocations.empty_list;

import com.abdelaziz.saturn.common.util.constants.ArrayConstants;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.phys.AABB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Predicate;

@SuppressWarnings("unchecked")
@Mixin(WorldGenRegion.class)
public class WorldGenRegionMixin {
    /**
     * @reason avoid unnecessary allocations.
     * @author AbdElAziz
     */
    @Overwrite
    public <T extends Entity> List<T> getEntities(EntityTypeTest<Entity, T> entityTypeTest, AABB aabb, Predicate<? super T> predicate) {
        return ArrayConstants.EMPTY_LIST;
    }

    /**
     * @reason avoid unnecessary allocations.
     * @author AbdElAziz
     */
    @Overwrite
    public List<Entity> getEntities(@Nullable Entity entity, AABB aabb, @Nullable Predicate<? super Entity> predicate) {
        return ArrayConstants.EMPTY_LIST;
    }

    /**
     * @reason avoid unnecessary allocations.
     * @author AbdElAziz
     */
    @Overwrite
    public List<Player> players() {
        return ArrayConstants.EMPTY_LIST;
    }
}
