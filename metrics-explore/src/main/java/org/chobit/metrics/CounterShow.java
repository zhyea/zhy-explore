package org.chobit.metrics;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Counter;
import com.codahale.metrics.MetricRegistry;

import java.util.concurrent.TimeUnit;

public class CounterShow {

    public static void main(String[] args) {

        final MetricRegistry metrics = new MetricRegistry();
        final ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics).build();
        reporter.start(3, TimeUnit.SECONDS);

        Counter counter = metrics.counter("异常监控");

        for (int i = 0; i < 100; i++) {
            try {
                if (0 == i % 3) {
                    throw new RuntimeException("自定义异常");
                }
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                counter.inc();
            }
        }
    }
}
