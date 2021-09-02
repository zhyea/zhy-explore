package org.chobit.spring.bf;

import org.chobit.spring.TestBase;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class BloomRedisFilterTest extends TestBase {

    @Autowired
    private BloomRedisFilter filter;


    @Test
    public void check() {
        int total = 10000 * 100000;
        int cnt = 0;
        for (int i = 0; i < total; i++) {
            boolean r = filter.checkAndSet(uuid());
            if (!r) {
                cnt++;
            }
            if (cnt % 1000 == 0) {
                System.out.println(cnt);
            }
        }
        System.out.println("---------------------------------------------------------------------");
        System.out.println(cnt);
        Assert.assertEquals(total, cnt);
    }


    @Test
    public void checkValid() {
        List<String> list = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            list.add(uuid());
        }
        int cnt = 0;
        for (int i = 0; i < 100; i++) {
            String tmp = list.get(i % 10);
            boolean r = filter.checkAndSet(tmp);
            if (r) {
                cnt++;
            }
        }
        System.out.println(cnt);
        Assert.assertEquals(90, cnt);
    }

    private String uuid() {
        return UUID.randomUUID().toString();
    }

}
