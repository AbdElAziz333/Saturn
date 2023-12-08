package com.abdelaziz.saturn.common.collections;

import it.unimi.dsi.fastutil.longs.LongLinkedOpenHashSet;

public class LeveledPriorityQueue {
    private final int levelCount;
    private final LongLinkedOpenHashSet[] queues;
    private int firstQueuedLevel;

    public LeveledPriorityQueue(int levelCount, final int queuesLength) {
        this.levelCount = levelCount;
        this.queues = new LongLinkedOpenHashSet[levelCount];

        for (int i = 0; i < levelCount; i++) {
            this.queues[i] = new LongLinkedOpenHashSet(queuesLength, 0.5F) {
                @Override
                protected void rehash(int newN) {
                    if (newN > queuesLength) {
                        super.rehash(newN);
                    }
                }
            };
        }

        this.firstQueuedLevel = levelCount;
    }

    public long removeFirstLong() {
        LongLinkedOpenHashSet longLinkedOpenHashSet = this.queues[this.firstQueuedLevel];
        long i = longLinkedOpenHashSet.removeFirstLong();

        if (longLinkedOpenHashSet.isEmpty()) {
            this.checkFirstQueuedLevel(this.levelCount);
        }

        return i;
    }

    public boolean isEmpty() {
        return this.firstQueuedLevel >= this.levelCount;
    }

    public void dequeue(long queue, int size, int firstQueue) {
        LongLinkedOpenHashSet longLinkedOpenHashSet = this.queues[size];
        longLinkedOpenHashSet.remove(queue);

        if (longLinkedOpenHashSet.isEmpty() && this.firstQueuedLevel == size) {
            this.checkFirstQueuedLevel(firstQueue);
        }
    }

    public void enqueue(long queue, int index) {
        this.queues[index].add(queue);
        if (this.firstQueuedLevel > index) {
            this.firstQueuedLevel = index;
        }
    }

    private void checkFirstQueuedLevel(int size) {
        int i = this.firstQueuedLevel;
        this.firstQueuedLevel = size;

        for (int j = i + 1; j < size; ++j) {
            if (!this.queues[j].isEmpty()) {
                this.firstQueuedLevel = j;
                break;
            }
        }
    }


}
