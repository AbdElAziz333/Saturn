package com.abdelaziz.saturn.mixin.allocations.entity.animal;

import com.abdelaziz.saturn.common.util.constants.EntityConstants;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.item.DyeColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(Sheep.class)
public class SheepMixin {
    /**
     * @reason Cache to a static final reference.
     * @author AbdElAziz
     * */
    @Overwrite
    private static float[] createSheepColor(DyeColor color) {
        if (color == DyeColor.WHITE) {
            return EntityConstants.WHITE_SHEEP_COLOR;
        } else {
            float[] afloat = color.getTextureDiffuseColors();
            return new float[]{afloat[0] * 0.75F, afloat[1] * 0.75F, afloat[2] * 0.75F};
        }
    }
}
