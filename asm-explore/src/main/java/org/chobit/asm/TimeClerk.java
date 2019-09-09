package org.chobit.asm;

import java.util.concurrent.atomic.LongAdder;

public class TimeClerk {

    static LongAdder total = new LongAdder();

    static LongAdder count = new LongAdder();

    public static void update(String methodId, long time) {
        System.out.println("--------------------------->>>>>" + methodId);
        total.add(time);
        count.increment();
    }

    public static long avg() {
        return total.longValue() / count.longValue();
    }

}
