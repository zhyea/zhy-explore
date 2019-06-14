package org.chobit.spring.tools;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.chobit.spring.bean.Worker;
import org.chobit.spring.controller.WorkerController;
import org.chobit.spring.exception.NonExistingWorkerException;
import org.chobit.spring.service.IWorkerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@WebMvcTest(WorkerController.class)
public class WorkerControllerMockMvcWithContextTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IWorkerService workerService;

    private JacksonTester<Worker> jsonWorker;

    @Before
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());

        System.out.println();
    }


    @Test
    public void getWhenExists() throws Exception {
        //given
        given(workerService.get(2)).willReturn(new Worker("LiLei", 16));
        //when
        MockHttpServletResponse response =
                mockMvc.perform(get("/worker/2").accept(MediaType.APPLICATION_JSON))
                        .andReturn().getResponse();
        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonWorker.write(new Worker("LiLei", 16)).getJson());
    }


    @Test
    public void getWhenNotExists() throws Exception {
        //given
        given(workerService.get(2)).willThrow(new NonExistingWorkerException());
        //when
        MockHttpServletResponse response =
                mockMvc.perform(get("/worker/2").accept(MediaType.APPLICATION_JSON))
                        .andReturn().getResponse();
        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
        assertThat(response.getContentAsString()).isEmpty();
    }


    @Test
    public void getByNameWhenExists() throws Exception {
        //given
        given(workerService.getByName("LiLei")).willReturn(Optional.of(new Worker("LiLei", 16)));
        //when
        MockHttpServletResponse response =
                mockMvc.perform(get("/worker?name=LiLei").accept(MediaType.APPLICATION_JSON))
                        .andReturn().getResponse();
        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonWorker.write(new Worker("LiLei", 16)).getJson());
    }


    @Test
    public void getByNameWhenNotExists() throws Exception {
        //given
        given(workerService.getByName("LiLei")).willReturn(Optional.empty());
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
