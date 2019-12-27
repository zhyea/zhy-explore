package org.chobit.bytebuddy.timing;

import java.util.concurrent.atomic.LongAdder;

public class TimingClerk {

    static LongAdder total = new LongAdder();

    static LongAdder count = new LongAdder();


    public static void update(boolean success, String methodId, long time) {
        if (success) {
            update(methodId, time);
        } else {
            updateFailed(methodId, time);
        }
    }

    public static void update(String methodId, long time) {
        System.out.println("--------------------------->>>>>" + methodId);
        total.add(time);
        count.increment();
    }


    public static void updateFailed(String methodId, long time) {
        System.out.println("--------------------------->>>>>failed:" + methodId);
        total.add(time);
        count.increment();
    }

    public static long avg() {
        return total.longValue() / count.longValue();
    }

}
