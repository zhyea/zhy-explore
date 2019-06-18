package org.chobit.metrics;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class GaugeShow {


    public static void main(String[] args) throws InterruptedException {
        final MetricRegistry metrics = new MetricRegistry();
        ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics).build();
        reporter.start(3, TimeUnit.SECONDS);

        Gauge<String> gauge = new Gauge<String>() {
            @Override
            public String getValue() {
                return new SimpleDateFormat("yyMMddHHmmSS").format(new Date());
            }
        };

        metrics.register("时间计时", gauge);

        for (int i = 0; i < 10; i++) {
            TimeUnit.SECONDS.sleep(3);
        }
    }


}
