package org.chobit.metrics;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class IoTest {


    public static void main(String[] args) throws IOException {
        File f = new File("/file");
        long start = System.currentTimeMillis();
        try {
            new IoTest().readInArray(f);
        } finally {
            System.out.println(System.currentTimeMillis() - start);
        }

    }

    public void readInByte(File file) throws IOException {
        FileInputStream input = new FileInputStream(file);
        int b = 0;
        // 每次循环读取一个字节并返回
        while ((b = input.read()) != -1) {
            // action with b
        }
    }


    public void readInArray(File file) throws IOException {
        FileInputStream input = new FileInputStream(file);
        int len = 0;
        byte[] arr = new byte[1024 * 1024 * 8];
        // 将数据放到缓存数组中再返回
        while ((len = input.read(arr)) != -1) {
            byte[] bytes = new byte[len];
            System.arraycopy(arr, 0, bytes, 0, len);
            // action with bytes
        }
    }


    public void readWithBuffer(File file) throws IOException {
        FileInputStream input = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(input);

        int len = 0;
        byte[] buffer = new byte[1024];
        // 这里通过BufferedInputStream利用了缓冲区来提速
        while ((len = bis.read(buffer)) != -1) {
            byte[] bytes = new byte[len];
            System.arraycopy(buffer, 0, bytes, 0, len);
            // action with bytes
        }
    }

}
