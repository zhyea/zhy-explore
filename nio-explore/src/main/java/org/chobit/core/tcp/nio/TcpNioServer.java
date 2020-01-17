package org.chobit.core.tcp.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Set;

public class TcpNioServer {

    // 端口号
    private int port = 1022;

    public TcpNioServer(int port) {
        this.port = port;
    }


    private static final Charset CHARSET = Charset.defaultCharset();

    // 用于检测所有Channel状态
    private Selector selector = null;

    public void init() throws IOException {
        selector = Selector.open();
        // 通过open方法打开一个未绑定的ServerSocketChannel实例
        ServerSocketChannel ssc = ServerSocketChannel.open();
        // 将ssc绑定到指定的IP地址
        ssc.bind(new InetSocketAddress("127.0.0.1", port));
        // 设置ssc以非阻塞方式工作
        ssc.configureBlocking(false);
        // 将ssc注册到指定的Selector对象，并设置状态为等待连接，SelectionKey维护了SocketChannel和Selector之间的关系
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        // 监控所有注册的Channel，如有Channel需要执行IO操作，则返回Channel的数量并将对应的SelectionKey加入被选择的key集合中，否则阻塞
        while (selector.select() > 0) {

            // 依次处理selector上所有已选择的SelectionKey集合
            Set<SelectionKey> keySet = selector.selectedKeys();
            for (SelectionKey key : keySet) {
                // 处理前先将key从集合移除，以避免重复处理
                keySet.remove(key);
                //
                // 检查key对应的Channel上是否存在客户端的连接请求
                if (key.isAcceptable()) {
                    // 调用accept接收连接，产生服务器端的SocketChannel
                    SocketChannel sc = ssc.accept();
                    // 设置采用非阻塞模式通信
                    sc.configureBlocking(false);
                    // 将新的SocketChannel也注册到Selector，并标明接下来需要进行读处理
                    sc.register(selector, SelectionKey.OP_READ);
                    // 重置SelectionKey状态，标明对应的ssc准备接收其它请求
                    key.interestOps(SelectionKey.OP_ACCEPT);
                }
                //
                // 检查key对应的Channel是否有数据需要读取
                if (key.isReadable()) {
                    StringBuilder msgBuilder = new StringBuilder();
                    // 获取key对应的channel
                    SocketChannel sc = (SocketChannel) key.channel();
                    try {
                        // 定义ByteBuffer，用于从Channel中读取数据
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        // 开始读取数据
                        while (sc.read(buffer) > 0) {
                            buffer.flip();
                            msgBuilder.append(CHARSET.decode(buffer));
                        }
                        // 将key对应的channel设置为待读取
                        key.interestOps(SelectionKey.OP_READ);
                    } catch (Exception e) {
                        // 如发生异常，表明key对应的Channel出现了异常，即Channel对应的客户端出现了问题，需要从Selector中取消该key的注册
                        key.cancel();
                        if (null != key.channel()) {
                            key.channel().close();
                        }
                    }

                    if (msgBuilder.length() > 0) {
                        // 输出读取到的数据
                        System.out.println("收到的数据是：" + msgBuilder.toString());
                        Set<SelectionKey> ks = selector.keys();
                        for (SelectionKey k : ks) {
                            Channel channel = k.channel();
                            if (channel instanceof SocketChannel) {
                                SocketChannel dest = (SocketChannel) channel;
                                dest.write(CHARSET.encode(msgBuilder.toString()));
                            }
                        }
                    }
                }
                //
                //
            }
        }
    }


    public static void main(String[] args) throws IOException {
        new TcpNioServer(1022).init();
    }

}
