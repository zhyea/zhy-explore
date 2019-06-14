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
public class WorkerControllerSpringBootTest {


    @Autowired
    private TestRestTemplate restTemplate;


    @Before
    public void setup() {
        System.out.println();
    }

    @Test
    public void getWhenExists() {
        //when
        ResponseEntity<Worker> response = restTemplate.getForEntity("/worker/2", Worker.class);
        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(new Worker("raccoon", 23));
    }


    @Test
    public void getWhenNotExists() {
        //when
        ResponseEntity<Worker> response = restTemplate.getForEntity("/worker/26", Worker.class);
        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNull();
    }


    @Test
    public void getByNameWhenExists() {
        //when
        ResponseEntity<Worker> response = restTemplate.getForEntity("/worker?name=HanMeimei", Worker.class);
        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(new Worker("HanMeimei", 16));
    }


    @Test
    public void getByNameWhenNotExists() {
        //when
        ResponseEntity<Worker> response = restTemplate.getForEntity("/worker?name=LiLei", Worker.class);
        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNull();
    }


    @Test
    public void add() {
        ResponseEntity<Worker> response =
                restTemplate.postForEntity("/worker", new Worker("Jerry", 12), Worker.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }


    @Test
    public void workerFilter() {
        //when
        ResponseEntity<Worker> response = restTemplate.getForEntity("/worker/2", Worker.class);
        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getHeaders().get("X-CHOBIT-APP")).containsOnly("chobit-header");
    }

}
