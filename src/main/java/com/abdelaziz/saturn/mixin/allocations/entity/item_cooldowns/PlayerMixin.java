package com.abdelaziz.saturn.mixin.allocations.entity.item_cooldowns;

import com.abdelaziz.saturn.common.util.constants.EntityConstants;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemCooldowns;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(Player.class)
public class PlayerMixin {
    /**
     * @reason Cache to a static final reference.
     * @author AbdElAziz
     * */
    @Overwrite
    protected ItemCooldowns createItemCooldowns() {
        return EntityConstants.ITEM_COOLDOWNS;
    }
}
