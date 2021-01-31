package org.chobit.vertx;

import io.vertx.core.Context;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpServer;

/**
 * @author robin
 */
public class VertxServer {


    public static void main(String[] args) {
        VertxOptions options = new VertxOptions().setWorkerPoolSize(40);
        DeploymentOptions deployOpts = new DeploymentOptions().setInstances(36);

        Vertx vertx = Vertx.vertx(options);


        Context context = vertx.getOrCreateContext();
        if (context.isEventLoopContext()) {
            System.out.println("Context attached to Event Loop");
        } else if (context.isWorkerContext()) {
            System.out.println("Context attached to Worker Thread");
        } else if (!Context.isOnVertxThread()) {
            System.out.println("Context not attached to a thread managed by vert.x");
        }

        vertx.deployVerticle(MyFirstVerticle.class.getName(), deployOpts, res -> {
            if (res.succeeded()) {
                System.out.println("Deployment id is: " + res.result());
            } else {
                System.out.println("Deployment failed!");
            }
        });


        vertx.setPeriodic(10000, id -> {
            System.out.println("timer fired!");
        });

        HttpServer server = vertx.createHttpServer();
        server.requestHandler(request -> {
            // This handler will be called every time an HTTP request is received at the server
            // 服务器每次收到一个HTTP请求时这个处理器将被调用
            request.response().end("hello world!");
        });
    }

}
