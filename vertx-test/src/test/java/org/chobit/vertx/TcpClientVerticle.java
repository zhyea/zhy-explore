package org.chobit.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetSocket;

/**
 * @author robin
 */
public class TcpClientVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        NetClient netClient = vertx.createNetClient();

        netClient.connect(8190, "127.0.0.1", connect -> {
            if (connect.succeeded()) {
                System.out.println("连接建立成功，开始发送数据！");
                NetSocket netSocket = connect.result();
                netSocket.write(Buffer.buffer("你好啊", "UTF-8"));
                netSocket.handler(resp -> {
                    System.out.println("接收到的数据为：" + resp.toString());
                });
            } else {
                System.out.println("服务器连接异常");
            }
        });
    }


    public static void main(String[] args) {
        Vertx.vertx().deployVerticle(new TcpClientVerticle());
    }
}
