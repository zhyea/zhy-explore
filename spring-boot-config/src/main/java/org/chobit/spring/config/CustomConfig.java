package org.chobit.spring.config;

import org.chobit.spring.bean.Worker;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "custom")
public class CustomConfig {

    private int id;

    private List<String> whiteUrl;

    private Worker worker;

    private String token;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getWhiteUrl() {
        return whiteUrl;
    }

    public void setWhiteUrl(List<String> whiteUrl) {
        this.whiteUrl = whiteUrl;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
