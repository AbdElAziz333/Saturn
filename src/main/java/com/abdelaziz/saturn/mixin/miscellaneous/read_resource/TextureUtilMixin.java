package com.abdelaziz.saturn.mixin.miscellaneous.read_resource;

import com.mojang.blaze3d.platform.TextureUtil;
import org.lwjgl.system.MemoryUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SeekableByteChannel;

@Mixin(TextureUtil.class)
public class TextureUtilMixin {
    /**
     * @reason Fix memory leakage problem in native operations.
     * @author AbdElAziz
     */
    @Overwrite
    public static ByteBuffer readResource(InputStream inputStream) throws IOException {
        ReadableByteChannel readableByteChannel = Channels.newChannel(inputStream);
        if (readableByteChannel instanceof SeekableByteChannel seekableByteChannel) {
            return readResource(readableByteChannel, (int)seekableByteChannel.size() + 1);
        } else {
            return readResource(readableByteChannel, 8192);
        }
    }

    private static ByteBuffer readResource(ReadableByteChannel readableByteChannel, int size) throws IOException {
        ByteBuffer byteBuffer = MemoryUtil.memAlloc(size);

        try {
            while(readableByteChannel.read(byteBuffer) != -1) {
                if (!byteBuffer.hasRemaining()) {
                    byteBuffer = MemoryUtil.memRealloc(byteBuffer, byteBuffer.capacity() * 2);
                }
            }

            return byteBuffer;
        } catch (IOException var4) {
            MemoryUtil.memFree(byteBuffer);
            throw var4;
        }
    }
}