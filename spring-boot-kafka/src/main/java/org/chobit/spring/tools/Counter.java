package org.chobit.spring.tools;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;

public class Counter {


    private String name;

    public Counter(String name) {
        this.name = name;
    }

    private ConcurrentHashMap<String, LongAdder> map = new ConcurrentHashMap<>(96);


    public void add(String key) {
        LongAdder adder = map.get(key);
        if (null == adder) {
            synchronized (map) {
                adder = new LongAdder();
                LongAdder old = map.putIfAbsent(key, adder);
                if (null != old) {
                    adder = old;
                }
            }
        }
        if (adder.longValue() % 1000 == 0) {
            dump();
        }
        adder.increment();
    }


    public void dump() {
        System.out.println(name + ":" + new Date() + " --- " + map.toString());
    }

}
