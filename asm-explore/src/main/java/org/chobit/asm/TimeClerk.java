package org.chobit.asm;

import java.util.concurrent.atomic.LongAdder;

public class TimeClerk {

    LongAdder total = new LongAdder();

    LongAdder count = new LongAdder();

    public void update(String methodId, long time) {
        System.out.println("--------------------------->>>>>" + methodId);
        total.add(time);
        count.increment();
    }

    public long avg() {
        return total.longValue() / count.longValue();
    }

}
