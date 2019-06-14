package org.chobit.spring.tools;


import org.chobit.spring.bean.Worker;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WorkerControllerTest {


    @Autowired
    private TestRestTemplate restTemplate;


    @Before
    public void setup() {
        System.out.println();
    }

    @Test
    public void get() {
        //when
        ResponseEntity<Worker> response = restTemplate.getForEntity("/worker/2", Worker.class);
        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(new Worker("raccoon", 23));
    }

}
