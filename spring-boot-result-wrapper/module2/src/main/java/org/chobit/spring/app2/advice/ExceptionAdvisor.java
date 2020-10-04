package org.chobit.spring.app2.advice;


import org.chobit.spring.app2.ext.Result;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class ExceptionAdvisor {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus
    public Result exceptionHandler(Exception e) {
        return new Result(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = RuntimeException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public Result runtimeExceptionHandler(RuntimeException e) {
        return new Result(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

}
