package org.chobit.core.tcp.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;

public class TcpNioClient {

    private int port = 1022;

    public TcpNioClient(int port) {
        this.port = port;
    }


    private static final Charset CHARSET = Charset.defaultCharset();

    // 用于检测SocketChannel的Selector对象
    private Selector selector = null;
    // 客户端SocketChannel
    private SocketChannel sc = null;

    public void init() throws IOException {
        selector = Selector.open();
        // 创建连接到指定服务器的SocketChannel
        sc = SocketChannel.open(new InetSocketAddress("127.0.0.1", port));
        // 设置sc以非阻塞方式工作
        sc.configureBlocking(false);
        // 将sc注册到指定的selector
        sc.register(selector, SelectionKey.OP_READ);

        new Thread(this::read).start();

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            sc.write(CHARSET.encode(line));
        }
    }


    public void read() {
        try {
            while (selector.select() > 0) {
                // 遍历每个有可用IO操作的Channel对应的SelectionKey
                for (SelectionKey key : selector.selectedKeys()) {
                    selector.selectedKeys().remove(key);
                    if (key.isReadable()) {
                        SocketChannel sc = (SocketChannel) key.channel();

                        StringBuilder builder = new StringBuilder();
                        // 定义ByteBuffer，用于从Channel中读取数据
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        // 开始读取数据
                        while (sc.read(buffer) > 0) {
                            buffer.flip();
                            builder.append(CHARSET.decode(buffer));
                        }
                        // 打印读取的内容
                        System.out.println("会话信息：" + builder.toString());
                        // 将key对应的channel设置为待读取
                        key.interestOps(SelectionKey.OP_READ);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws IOException {
        new TcpNioClient(1022).init();
    }
}
