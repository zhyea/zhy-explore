package org.chobit.core;


import java.io.*;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * java nio FileChannel测试
 */
public class FileChannelCase {

    public static void main(String[] args) throws IOException {
        File f = new File("/zhy/readme.md");

        // 文件读和写都是Channel，但是来自不同的Stream
        FileChannel in = new FileInputStream(f).getChannel();
        FileChannel out = new RandomAccessFile("/b.txt", "rw").getChannel();

        MappedByteBuffer buffer = in.map(FileChannel.MapMode.READ_ONLY, 0, f.length());

        out.write(buffer);

        buffer.clear();
        CharBuffer charBuffer = Charset.defaultCharset().newDecoder().decode(buffer);
        System.out.println(charBuffer);
    }

}
