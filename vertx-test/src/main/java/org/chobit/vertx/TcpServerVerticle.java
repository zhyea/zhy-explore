package org.chobit.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetServer;
import io.vertx.core.net.NetServerOptions;

import static org.chobit.vertx.Config.*;

/**
 * @author robin
 */
public class TcpServerVerticle extends AbstractVerticle {

    @Override
    public void start() {
        NetServerOptions options = new NetServerOptions().setPort(PORT);
        NetServer server = vertx.createNetServer(options);

        server.connectHandler(socket -> {
            socket.handler(buffer -> {
                String msg = buffer.toString();
                System.out.println(msg);
                socket.write(Buffer.buffer("We have received message!" + NEW_LINE));
                if (msg.endsWith(FLAG_END)) {
                    socket.close();
                }
            });

            socket.closeHandler(handler -> {
                System.out.println("Connection closed.");
            });
        });

        server.listen(res -> {
            if (res.succeeded()) {
                System.out.println("TCP Server has been started!");
            }
        });
    }
}
