package org.chobit.vertx;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

/**
 * @author robin
 */
public class VertxServer {


    public static void main(String[] args) {
        VertxOptions options = new VertxOptions().setWorkerPoolSize(40);
        Vertx vertx = Vertx.vertx(options);
        vertx.deployVerticle(MyFirstVerticle.class.getName());
    }

}
