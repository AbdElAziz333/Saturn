package com.abdelaziz.saturn.mixin.allocations.composter;

import com.abdelaziz.saturn.common.util.constants.SaturnConstants;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.ComposterBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

public class ComposterBlockMixin {
    @Mixin(ComposterBlock.EmptyContainer.class)
    static class EmptyContainerMixin {
        /**
         * @reason Cache to a static final reference.
         * @author AbdElAziz
         * */
        @Overwrite
        public int[] getSlotsForFace(Direction direction) {
            return SaturnConstants.ZERO;
        }
    }

    @Mixin(ComposterBlock.InputContainer.class)
    static class InputContainerMixin {
        /**
         * @reason Cache to a static final reference.
         * @author AbdElAziz
         * */
        @Overwrite
        public int[] getSlotsForFace(Direction direction) {
            return direction == Direction.UP ? SaturnConstants.MULTI_ZERO : SaturnConstants.ZERO;
        }
    }

    @Mixin(ComposterBlock.OutputContainer.class)
    static class OutputContainerMixin {
        /**
         * @reason Cache to a static final reference.
         * @author AbdElAziz
         * */
        @Overwrite
        public int[] getSlotsForFace(Direction direction) {
            return direction == Direction.DOWN ? SaturnConstants.MULTI_ZERO : SaturnConstants.ZERO;
        }
    }
}
