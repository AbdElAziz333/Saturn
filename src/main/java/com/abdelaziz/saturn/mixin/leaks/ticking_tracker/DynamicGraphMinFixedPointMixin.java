package com.abdelaziz.saturn.mixin.leaks.ticking_tracker;

import com.abdelaziz.saturn.common.collections.LeveledPriorityQueue;
import it.unimi.dsi.fastutil.longs.Long2ByteMap;
import it.unimi.dsi.fastutil.longs.LongLinkedOpenHashSet;
import net.minecraft.util.Mth;
import net.minecraft.world.level.lighting.DynamicGraphMinFixedPoint;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DynamicGraphMinFixedPoint.class)
public abstract class DynamicGraphMinFixedPointMixin {
    private LeveledPriorityQueue priorityQueue;

    @Shadow @Final
    private int levelCount;

    @Shadow @Final @Mutable
    private LongLinkedOpenHashSet[] queues;

    @Shadow
    private volatile boolean hasWork;

    @Shadow
    protected abstract boolean isSource(long p_75551_);

    @Shadow
    protected abstract int getComputedLevel(long p_75566_, long p_75567_, int p_75568_);

    @Shadow
    protected abstract int getLevel(long p_75599_);

    @Shadow
    protected abstract void checkNeighborsAfterUpdate(long p_75563_, int p_75564_, boolean p_75565_);

    @Shadow
    protected abstract void setLevel(long p_75552_, int p_75553_);

    @Shadow @Final
    private Long2ByteMap computedLevels;

    @Shadow
    protected abstract int computeLevelFromNeighbor(long p_75590_, long p_75591_, int p_75592_);


    @Inject(
            method = "<init>",
            at = @At(
                    value = "TAIL"
            )
    )
    private void initQueue(int num1, int num2, int num3, CallbackInfo ci) {
        this.queues = null;
        this.priorityQueue = new LeveledPriorityQueue(num1, num2);
    }

    @ModifyConstant(
            method = "<init>",
            constant = @Constant(
                    intValue = 0,
                    expandZeroConditions = Constant.Condition.GREATER_THAN_ZERO
            )
    )
    private int preventLoop(int constant) {
        return 2;
    }

    /**
     * @reason Use {@link LeveledPriorityQueue} instead
     * @author AbdElAziz
     * */
    @Overwrite
    protected void removeFromQueue(long value) {
        int i = this.computedLevels.remove(value) & 255;

        if (i != 255) {
            int j = this.getLevel(value);
            int k = this.getKey(j, i);
            this.priorityQueue.dequeue(value, k, this.levelCount);
            this.hasWork = !this.priorityQueue.isEmpty();
        }
    }


    /**
     * @reason Use {@link Math#min(int, int)}
     * @author AbdELAziz
     * */
    @Overwrite
    private int getKey(int p_75549_, int p_75550_) {
        return Math.min(Math.min(p_75549_, p_75550_), this.levelCount - 1);
    }

    /**
     * @reason Use {@link LeveledPriorityQueue} instead
     * @author AbdElAziz
     * */
    @Overwrite
    protected void checkEdge(long p_75577_, long p_75578_, int p_75579_, boolean p_75580_) {
        this.checkEdge(p_75577_, p_75578_, p_75579_, this.getLevel(p_75578_), this.computedLevels.get(p_75578_) & 255, p_75580_);
        this.hasWork = !this.priorityQueue.isEmpty();
    }

    /**
     * @reason Use {@link LeveledPriorityQueue} instead
     * @author AbdElAziz
     * */
    @Overwrite
    private void checkEdge(long p_75570_, long p_75571_, int p_75572_, int p_75573_, int p_75574_, boolean p_75575_) {
        if (!this.isSource(p_75571_)) {
            p_75572_ = Mth.clamp(p_75572_, 0, this.levelCount - 1);
            p_75573_ = Mth.clamp(p_75573_, 0, this.levelCount - 1);
            boolean flag = p_75574_ == 255;
            if (flag) {
                p_75574_ = p_75573_;
            }

            int i;
            if (p_75575_) {
                i = Math.min(p_75574_, p_75572_);
            } else {
                i = Mth.clamp(this.getComputedLevel(p_75571_, p_75570_, p_75572_), 0, this.levelCount - 1);
            }

            int j = this.getKey(p_75573_, p_75574_);
            if (p_75573_ != i) {
                int k = this.getKey(p_75573_, i);
                if (j != k && !flag) {
                    this.priorityQueue.dequeue(p_75571_, j, k);
                }

                this.priorityQueue.enqueue(p_75571_, k);
                this.computedLevels.put(p_75571_, (byte)i);
            } else if (!flag) {
                this.priorityQueue.dequeue(p_75571_, j, this.levelCount);
                this.computedLevels.remove(p_75571_);
            }

        }
    }

    /**
     * @reason Use {@link LeveledPriorityQueue} instead
     * @author AbdElAziz
     * */
    @Overwrite
    protected final void checkNeighbor(long p_75594_, long p_75595_, int p_75596_, boolean p_75597_) {
        int i = this.computedLevels.get(p_75595_) & 255;
        int j = Mth.clamp(this.computeLevelFromNeighbor(p_75594_, p_75595_, p_75596_), 0, this.levelCount - 1);
        if (p_75597_) {
            this.checkEdge(p_75594_, p_75595_, j, this.getLevel(p_75595_), i, p_75597_);
        } else {
            boolean flag = i == 255;
            int k;
            if (flag) {
                k = Mth.clamp(this.getLevel(p_75595_), 0, this.levelCount - 1);
            } else {
                k = i;
            }

            if (j == k) {
                this.checkEdge(p_75594_, p_75595_, this.levelCount - 1, flag ? k : this.getLevel(p_75595_), i, p_75597_);
            }
        }
    }

    /**
     * @reason Use {@link LeveledPriorityQueue} instead
     * @author AbdElAziz
     * */
    @Overwrite
    protected final int runUpdates(int value) {
        if (this.priorityQueue.isEmpty()) {
            return value;
        } else {
            while(!this.priorityQueue.isEmpty() && value > 0) {
                --value;
                long i = this.priorityQueue.removeFirstLong();
                int j = Mth.clamp(this.getLevel(i), 0, this.levelCount - 1);
                int k = this.computedLevels.remove(i) & 255;
                if (k < j) {
                    this.setLevel(i, k);
                    this.checkNeighborsAfterUpdate(i, k, true);
                } else if (k > j) {
                    this.setLevel(i, this.levelCount - 1);
                    if (k != this.levelCount - 1) {
                        this.priorityQueue.enqueue(i, this.getKey(this.levelCount - 1, k));
                        this.computedLevels.put(i, (byte)k);
                    }

                    this.checkNeighborsAfterUpdate(i, j, false);
                }
            }

            this.hasWork = !this.priorityQueue.isEmpty();
            return value;
        }
    }
}
