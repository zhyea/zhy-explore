package org.chobit.vertx;

import io.vertx.core.Vertx;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static io.vertx.core.http.HttpMethod.GET;

/**
 * @author robin
 */
@RunWith(VertxUnitRunner.class)
public class MyFirstVerticleTest {


    private Vertx vertx;

    @Before
    public void setUp(TestContext context) {
        vertx = Vertx.vertx();
        vertx.deployVerticle(MyFirstVerticle.class.getName(), context.asyncAssertSuccess());
    }

    @After
    public void tearDown(TestContext context) {
        vertx.close(context.asyncAssertSuccess());
    }

    @Test
    public void testApplication(TestContext context) {

        vertx.createHttpClient().request(GET, 8080, "localhost", "/",
                context.asyncAssertSuccess(req -> {
                    req.send(context.asyncAssertSuccess(resp -> {
                        context.assertEquals(200, resp.statusCode());
                    }));
                }));
    }


}
