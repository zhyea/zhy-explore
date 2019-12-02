package org.chobit.spring;

import org.chobit.spring.model.ResultWrapper;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.chobit.spring.tools.JsonUtils.fromJson;
import static org.chobit.spring.tools.JsonUtils.toJson;

public abstract class ApiTestBase extends TestBase {


    @Autowired
    private TestRestTemplate restTemplate;


    protected abstract String parentPath();


    protected <T> Object testPost(String path, T param) {
        path = buildPath(path);
        System.out.println(toJson(param));
        ResponseEntity<ResultWrapper> response = restTemplate.postForEntity(path, param, ResultWrapper.class);
        ResultWrapper w = response.getBody();
        return getResponse(w);
    }

    protected <T, R> R testPost(String path, T param, Class<R> tClass) {
        Object r = testPost(path, param);
        String json = toJson(r);
        return fromJson(json, tClass);
    }

    protected <T> Object testPut(String path, T param) {
        path = buildPath(path);
        System.out.println(toJson(param));
        ResponseEntity<ResultWrapper> response = restTemplate.exchange(path, HttpMethod.PUT, new HttpEntity<T>(param), ResultWrapper.class);
        ResultWrapper w = response.getBody();
        return getResponse(w);
    }

    protected <T, R> R testPut(String path, T param, Class<R> tClass) {
        Object r = testPut(path, param);
        String json = toJson(r);
        return fromJson(json, tClass);
    }

    protected Object testGet(String path) {
        path = buildPath(path);
        ResponseEntity<ResultWrapper> response = restTemplate.getForEntity(path, ResultWrapper.class);
        ResultWrapper w = response.getBody();
        return getResponse(w);
    }

    protected <T> T testGet(String path, Class<T> tClass) {
        Object r = testGet(path);
        String json = toJson(r);
        return fromJson(json, tClass);
    }


    protected Object testDelete(String path) {
        path = buildPath(path);
        ResponseEntity<ResultWrapper> response = restTemplate.exchange(path, HttpMethod.DELETE, null, ResultWrapper.class);
        ResultWrapper w = response.getBody();
        return getResponse(w);
    }


    protected <T> T testDelete(String path, Class<T> tClass) {
        Object r = testDelete(path);
        String json = toJson(r);
        return fromJson(json, tClass);
    }


    private String buildPath(String path) {
        String p = parentPath();
        if (!p.endsWith("/") && !path.startsWith("/")) {
            return p + "/" + path;
        } else {
            return p + path;
        }
    }

    private Object getResponse(ResultWrapper wrapper) {
        System.out.println(toJson(wrapper));

        Assert.assertNotNull(wrapper);
        Assert.assertEquals(HttpStatus.OK.value(), wrapper.getCode());

        return null == wrapper.getResult() ? "" : wrapper.getResult();
    }


}
