package org.chobit.core;


import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Java NIO FileVisitor使用测试
 */
public class FileVisitorCase {


    public static void main(String[] args) throws IOException {

        Files.walkFileTree(Paths.get("D:", "zhy"),
                new SimpleFileVisitor<Path>() {

                    @Override
                    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
                            throws IOException {
                        System.out.println("进入目录：" + dir);
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                            throws IOException {
                        System.out.println("文件：" + file);
                        return FileVisitResult.CONTINUE;
                    }

                });
    }


}
