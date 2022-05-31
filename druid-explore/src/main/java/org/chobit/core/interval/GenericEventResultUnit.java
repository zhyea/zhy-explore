package org.chobit.core.interval;

/**
 * @author robin
 */
public class GenericEventResultUnit<T> {

    private T event;

    private String version;

    private String timestamp;

    public T getEvent() {
        return event;
    }

    public void setEvent(T event) {
        this.event = event;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
