package org.chobit.metrics;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.ExponentiallyDecayingReservoir;
import com.codahale.metrics.Histogram;
import com.codahale.metrics.MetricRegistry;

import java.util.concurrent.TimeUnit;

public class HistogramShow {

    public static void main(String[] args) throws InterruptedException {

        final MetricRegistry metrics = new MetricRegistry();
        final ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics).build();
        reporter.start(3, TimeUnit.SECONDS);

        Histogram histogram = new Histogram(new ExponentiallyDecayingReservoir());
        metrics.register("方法异常统计", histogram);

        for (int i = 0; i < 100; i++) {
            long start = System.currentTimeMillis();
            try {
                delayedMethod();
            } finally {
                histogram.update(System.currentTimeMillis() - start);
            }
        }
    }


    private static void delayedMethod() throws InterruptedException {
        long time = (long) (Math.random() * 1000);

        System.out.println("------>>method used time: " + time);
        TimeUnit.MILLISECONDS.sleep(time);
    }
}
