package org.chobit.vertx;

import io.vertx.core.AbstractVerticle;

/**
 * @author robin
 */
public class MyFirstVerticle extends AbstractVerticle {

    @Override
    public void start() {
        vertx.createHttpServer().requestHandler(req -> {
            req.response()
                    .putHeader("content-type", "text/plain")
                    .end("Hello World!");
            System.out.println(this + "" + 1112222222);
        }).listen(8080);
    }
}
