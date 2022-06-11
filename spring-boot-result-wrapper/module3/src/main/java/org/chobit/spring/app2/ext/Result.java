package org.chobit.spring.app2.ext;

import org.springframework.http.HttpStatus;

/**
 * @author robin
 */
public class Result {

    private int status = HttpStatus.OK.value();

    private Object content;

    private String msg;

    private int code;

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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
