package org.chobit.vertx;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

/**
 * @author robin
 */
public class Bootstrap {


    public static void main(String[] args) {
        VertxOptions options = new VertxOptions().setWorkerPoolSize(40);
        DeploymentOptions deployOpts = new DeploymentOptions().setInstances(1);

        Vertx vertx = Vertx.vertx(options);

        vertx.deployVerticle(TcpServerVerticle.class.getName(), deployOpts, res -> {
            if (res.succeeded()) {
                System.out.println("Deployment id is: " + res.result());
            } else {
                System.out.println("Deployment failed!");
            }
        });


    }

}
