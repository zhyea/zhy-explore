package org.chobit.spring.app1.ext;

import org.springframework.http.HttpStatus;

public class Result {

    private int status = HttpStatus.OK.value();

    private Object content;

    public Result(Object content) {
        this.content = content;
    }

    public Result(int status, String content) {
        this(content);
        this.content = content;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
