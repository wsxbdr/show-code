package com.xuben.nettyDemo.byteBufferTest;

import java.nio.ByteBuffer;

public class ByteBufferTest {

    public static void main(String[] args) {
        // 堆内存
        ByteBuffer buffer = ByteBuffer.allocate(12);

        buffer.putInt(1);
        buffer.putInt(2);
        buffer.putInt(3);

        buffer.flip();

        System.out.println(buffer.getInt());
        System.out.println(buffer.getInt());
        System.out.println(buffer.getInt());
    }
}
