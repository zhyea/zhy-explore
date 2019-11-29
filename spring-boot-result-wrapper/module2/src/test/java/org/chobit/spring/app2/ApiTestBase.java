package org.chobit.spring.app2;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.chobit.spring.app2.ext.Result;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class ApiTestBase {

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private ObjectMapper mapper;


    protected abstract String parentPath();


    protected Object testGet(String path) {
        path = buildPath(path);
        ResponseEntity<Result> response = restTemplate.getForEntity(path, Result.class);
        Result w = response.getBody();
        return getResponse(w);
    }


    private String buildPath(String path) {
        String p = parentPath();
        if (!p.endsWith("/") && !path.startsWith("/")) {
            return p + "/" + path;
        } else {
            return p + path;
        }
    }


    private Object getResponse(Result result) {
        try {
            System.out.println(mapper.writeValueAsString(result));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(result);
        Assert.assertEquals(HttpStatus.OK.value(), result.getStatus());
        return null == result.getContent() ? "" : result.getContent();
    }
}
