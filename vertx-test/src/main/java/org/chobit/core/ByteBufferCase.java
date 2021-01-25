package org.chobit.core;

import java.nio.ByteBuffer;

/**
 * java nio ByteBuffer的使用测试
 */
public class ByteBufferCase {


    /**
     * 主要观察调用Buffer的不同方法时，capacity、limit和position三个值的变化
     */
    public static void main(String[] args) {

        ByteBuffer buffer = ByteBuffer.allocate(10);
        printDetail(buffer);

        System.out.println("put 3 num----------------------------------------");
        buffer.put((byte) 1);
        buffer.put((byte) 2);
        buffer.put((byte) 3);
        printDetail(buffer);

        System.out.println("call flip----------------------------------------");
        // flip的作用有两个： 1.重置读取位置，2.设置可以读取到的位置
        buffer.flip();
        printDetail(buffer);


        System.out.println("call get----------------------------------------" + buffer.get());
        printDetail(buffer);


        System.out.println("call clear----------------------------------------");
        buffer.clear();
        printDetail(buffer);

    }


    private static void printDetail(ByteBuffer buffer) {
        System.out.println("Capacity: " + buffer.capacity());
        System.out.println("Limit: " + buffer.limit());
        System.out.println("Position: " + buffer.position());
    }


}
