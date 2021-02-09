package org.chobit.vertx;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetSocket;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.chobit.vertx.Config.HOST;
import static org.chobit.vertx.Config.PORT;

/**
 * @author robin
 */
@RunWith(VertxUnitRunner.class)
public class TcpServerVerticleTest {


    private Vertx vertx;

    @Before
    public void init(TestContext context) {
        System.out.println("Init vertx");
        vertx = Vertx.vertx();
        vertx.deployVerticle(TcpServerVerticle.class.getName(), context.asyncAssertSuccess());
    }

    @After
    public void tearDown(TestContext context) {
        System.out.println("Close vertx");
        vertx.close(context.asyncAssertSuccess());
    }

    @Test
    public void interact(TestContext context) {

        vertx.createNetClient().connect(PORT, HOST, conn -> {
            if (conn.succeeded()) {
                NetSocket socket = conn.result();
                socket.write(Buffer.buffer("Hi"));
                socket.handler(res -> {
                    System.out.println("Message Received:" + res.toString());
                });
            } else {
                System.out.println("TCP server connect error!");
            }
        });

    }


}
