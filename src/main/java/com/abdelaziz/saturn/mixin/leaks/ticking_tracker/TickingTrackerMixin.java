package com.abdelaziz.saturn.mixin.leaks.ticking_tracker;

import it.unimi.dsi.fastutil.longs.Long2ByteMap;
import net.minecraft.server.level.TickingTracker;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(TickingTracker.class)
public class TickingTrackerMixin {
    @Shadow @Final
    protected Long2ByteMap chunks;

    /**
     * @reason Add the equality check (>=)
     * @author AbdElAziz
     * */
    @Overwrite
    protected void setLevel(long queue1, int queue2) {
        if (queue2 >= 33) {
            this.chunks.remove(queue1);
        } else {
            this.chunks.put(queue1, (byte)queue2);
        }

    }

}
