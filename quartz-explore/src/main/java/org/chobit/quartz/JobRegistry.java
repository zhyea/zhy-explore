package org.chobit.quartz;

import java.util.HashMap;
import java.util.Map;

public class JobRegistry {

    private final MyConfig config;

    private final Map<Class<? extends AbstractJob>, AbstractJob> myJobMap;

    public JobRegistry(MyConfig config) {
        this.config = config;
        myJobMap = myJobMap();
    }

    public AbstractJob getInstance(Class<? extends AbstractJob> clazz) {
        return myJobMap.get(clazz);
    }

    public Iterable<AbstractJob> jobs() {
        return myJobMap.values();
    }

    private Map<Class<? extends AbstractJob>, AbstractJob> myJobMap() {
        Map<Class<? extends AbstractJob>, AbstractJob> m = new HashMap<>(2);
        m.put(MyJob.class, new MyJob(config));
        return m;
    }
}
