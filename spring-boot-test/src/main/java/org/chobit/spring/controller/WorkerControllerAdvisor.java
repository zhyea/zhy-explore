package org.chobit.spring.controller;


import org.chobit.spring.exception.NonExistingWorkerException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class WorkerControllerAdvisor {


    @ExceptionHandler(NonExistingWorkerException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleNonExistingWorker() {
        System.out.println("############################>>>> Handle NonExistingWorkerException.");
    }


}
