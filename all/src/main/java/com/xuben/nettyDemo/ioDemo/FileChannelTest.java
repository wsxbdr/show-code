package com.xuben.nettyDemo.ioDemo;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

public class FileChannelTest {
    public static void main(String[] args) throws IOException {
        FileChannel fileChannel = new RandomAccessFile("D:\\programs\\java\\show-code\\all\\src\\main\\java\\com\\xuben\\nettyDemo\\ioDemo\\demo.txt", "rw").getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(10);
        while (fileChannel.read(buffer) != -1) {
            buffer.flip();
            while (buffer.hasRemaining()) {
                int remain = buffer.remaining();
                byte[] bytes = new byte[remain];
                buffer.get(bytes);
                System.out.println(new String(bytes, StandardCharsets.UTF_8));
            }
            buffer.clear();
        }
    }
}
