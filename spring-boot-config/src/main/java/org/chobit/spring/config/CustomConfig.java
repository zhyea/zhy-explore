package org.chobit.spring.config;

import org.chobit.spring.bean.Worker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
@ConfigurationProperties(prefix = "custom")
public class CustomConfig {

    @Value("${spring.profiles.active}")
    private String profile;

    private int id;

    private String token;

    private String seq;

    private List<String> whiteUrl;

    private List<String> blackUrl;

    private Worker worker1;

    private Worker worker2;

    private Worker worker3;

    private String content;

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

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

    public Worker getWorker1() {
        return worker1;
    }

    public void setWorker1(Worker worker1) {
        this.worker1 = worker1;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public List<String> getBlackUrl() {
        return blackUrl;
    }

    public void setBlackUrl(List<String> blackUrl) {
        this.blackUrl = blackUrl;
    }

    public Worker getWorker2() {
        return worker2;
    }

    public void setWorker2(Worker worker2) {
        this.worker2 = worker2;
    }

    public Worker getWorker3() {
        return worker3;
    }

    public void setWorker3(Worker worker3) {
        this.worker3 = worker3;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
