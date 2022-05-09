package org.chobit.spring;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RedisTest {

    @Autowired
    @Qualifier("firstStringRedisTemplate")
    private StringRedisTemplate firstRedisTemplate;

    @Autowired
    @Qualifier("secondStringRedisTemplate")
    private StringRedisTemplate secondRedisTemplate;


    @Test
    public void execute() {
        firstRedisTemplate.opsForValue().set("site", "chobit.org");
        System.out.println(firstRedisTemplate.opsForValue().get("site"));
        secondRedisTemplate.opsForValue().set("site", "zhyea.com");
        System.out.println(secondRedisTemplate.opsForValue().get("site"));
    }


}
