package org.chobit.spring.verifier;


import lombok.extern.slf4j.Slf4j;
import org.chobit.commons.concurrent.Threads;
import org.chobit.spring.autoconfigure.trace.TraceClew;
import org.chobit.spring.tools.BeanKit;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class TraceClewVerifier {


    private final Executor executor = Executors.newFixedThreadPool(6);

    @TraceClew
    public void start() {
        logger.info("trace clew verifier start ...");
        for (int i = 0; i < 36; i++) {

            executor.execute(() -> {
                TraceClewVerifier proxy = BeanKit.get(TraceClewVerifier.class);
                proxy.subRun();
            });
        }

        Threads.sleep(TimeUnit.SECONDS, 1L);
        logger.info("trace clew verifier end ...");
    }


    @TraceClew
    public void subRun() {
        logger.info("trace clew sub thread is running ...");
    }
}
