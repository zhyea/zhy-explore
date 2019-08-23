package org.chobit.quartz;

public class MyConfig {

    private int intervalSeconds;

    public MyConfig(int intervalSeconds) {
        this.intervalSeconds = intervalSeconds;
    }

    public int getIntervalSeconds() {
        return intervalSeconds;
    }

}
