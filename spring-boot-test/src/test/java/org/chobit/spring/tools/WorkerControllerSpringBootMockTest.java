package org.chobit.spring.tools;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.chobit.spring.bean.Worker;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WorkerControllerSpringBootMockTest {

    @Autowired
    private MockMvc mockMvc;

    private JacksonTester<Worker> jsonWorker;

    @Before
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());

        System.out.println();
    }


    @Test
    public void getWhenExists() throws Exception {
        //when
        MockHttpServletResponse response =
                mockMvc.perform(get("/worker/2").accept(MediaType.APPLICATION_JSON))
                        .andReturn().getResponse();
        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonWorker.write(new Worker("raccoon", 23)).getJson());
    }


    @Test
    //@DirtiesContext
    public void getWhenNotExists() throws Exception {
        //when
        MockHttpServletResponse response =
                mockMvc.perform(get("/worker/26").accept(MediaType.APPLICATION_JSON))
                        .andReturn().getResponse();
        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
        assertThat(response.getContentAsString()).isEmpty();
    }


    @Test
    public void getByNameWhenExists() throws Exception {
        //when
        MockHttpServletResponse response =
                mockMvc.perform(get("/worker?name=HanMeimei").accept(MediaType.APPLICATION_JSON))
                        .andReturn().getResponse();
        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonWorker.write(new Worker("HanMeimei", 16)).getJson());
    }


    @Test
    public void getByNameWhenNotExists() throws Exception {
        //when
        MockHttpServletResponse response =
                mockMvc.perform(get("/worker?name=LiLei").accept(MediaType.APPLICATION_JSON))
                        .andReturn().getResponse();
        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEmpty();
    }


    @Test
    public void add() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(
                post("/worker").contentType(MediaType.APPLICATION_JSON)
                        .content(jsonWorker.write(new Worker("Jerry", 12)).getJson())
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }


    @Test
    public void workerFilter() throws Exception {
        //when
        MockHttpServletResponse response =
                mockMvc.perform(get("/worker/2").accept(MediaType.APPLICATION_JSON))
                        .andReturn().getResponse();
        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getHeaders("X-CHOBIT-APP")).containsOnly("chobit-header");
    }

}
