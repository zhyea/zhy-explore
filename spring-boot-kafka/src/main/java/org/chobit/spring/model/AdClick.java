package org.chobit.spring.model;

import java.util.UUID;

public class AdClick {

    private int id;

    private String traceId = UUID.randomUUID().toString();

    private long eventTime = System.currentTimeMillis();


    public AdClick(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public long getEventTime() {
        return eventTime;
    }

    public void setEventTime(long eventTime) {
        this.eventTime = eventTime;
    }
}
