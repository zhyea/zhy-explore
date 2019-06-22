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


    }


}
