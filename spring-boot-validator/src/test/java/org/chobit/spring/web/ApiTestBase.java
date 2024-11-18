package org.chobit.spring.web;

import com.fasterxml.jackson.core.type.TypeReference;
import org.chobit.commons.model.response.Result;
import org.chobit.commons.utils.JsonKit;
import org.chobit.spring.TestBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Map;

import static org.chobit.commons.utils.JsonKit.toJson;


public abstract class ApiTestBase extends TestBase {


    @Autowired
    private TestRestTemplate restTemplate;


    protected abstract String parentPath();


    protected <T> String testPost(String path, T body) {
        return testPost(path, null, body);
    }


    protected <T> String testPost(String path, Map<String, String> headers, T entity) {
        path = buildPath(path);
        System.out.println(toJson(entity));
        ResponseEntity<String> response = restTemplate.postForEntity(path, new HttpEntity<>(entity, headers(headers)), String.class);
        return response.getBody();
    }


    protected <T> String testPostForm(String path, Map<String, String> headers, Map<String, Object> params) {
        path = buildPath(path);

        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>(8);
        for (Map.Entry<String, Object> e : params.entrySet()) {
            map.add(e.getKey(), e.getValue());
        }

        System.out.println(toJson(params));
        ResponseEntity<String> response = restTemplate.postForEntity(path, new HttpEntity<>(map, headers(headers)), String.class);
        return response.getBody();
    }


    protected <T> String testPut(String path, Map<String, String> headers, T param) {
        path = buildPath(path);
        System.out.println(toJson(param));
        ResponseEntity<String> response = restTemplate.exchange(path, HttpMethod.PUT, new HttpEntity<>(param, headers(headers)), String.class);
        return response.getBody();
    }


    protected String testGet(String path) {
        return testGet(path, null);
    }


    protected String testGet(String path, Map<String, String> headers) {
        path = buildPath(path);
        ResponseEntity<String> response = restTemplate.exchange(path, HttpMethod.GET, new HttpEntity(headers(headers)), String.class);
        return response.getBody();
    }


    protected String testDelete(String path) {
        path = buildPath(path);
        ResponseEntity<String> response = restTemplate.exchange(path, HttpMethod.DELETE, null, String.class);
        return response.getBody();
    }

    protected String testDelete(String path, Map<String, String> headers) {
        path = buildPath(path);
        ResponseEntity<String> response = restTemplate.exchange(path, HttpMethod.DELETE, new HttpEntity(headers(headers)), String.class);
        return response.getBody();
    }


    private String buildPath(String path) {
        String p = parentPath();
        if (!p.endsWith("/") && !path.startsWith("/")) {
            p = p + "/" + path;
        } else {
            p = p + path;
        }
        System.out.println(p);
        return p;
    }


    private HttpHeaders headers(Map<String, String> headers) {
        HttpHeaders result = new HttpHeaders();
        if (null == headers) {
            return result;
        }
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            result.add(entry.getKey(), entry.getValue());
        }
        return result;
    }


    protected <T> T fromResult(String json, Class<T> clazz) {
        System.out.println(json);
        Result<Object> resultWrapper = JsonKit.fromJson(json, new TypeReference<Result<Object>>() {
        });
        String content = JsonKit.toJson(resultWrapper.getData());
        return JsonKit.fromJson(content, clazz);
    }


    protected <T> T fromResult(String json, TypeReference<T> t) {
        System.out.println(json);
        Result<Object> resultWrapper = JsonKit.fromJson(json, new TypeReference<Result<Object>>() {
        });
        String content = JsonKit.toJson(resultWrapper.getData());
        return JsonKit.fromJson(content, t);
    }


}
