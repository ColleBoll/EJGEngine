package org.collebol.utils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtils {
    public static ByteBuffer loadFileToByteBuffer(String path) {
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(path));
            ByteBuffer buffer = ByteBuffer.allocateDirect(bytes.length);
            buffer.put(bytes);
            buffer.flip();
            return buffer;
        } catch (IOException e) {
            throw new RuntimeException("Failed to load file: " + path, e);
        }
    }
}
