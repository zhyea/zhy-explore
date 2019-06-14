package org.chobit.spring.service;


import org.chobit.spring.bean.Worker;
import org.springframework.beans.factory.DisposableBean;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;


public class WorkerService implements DisposableBean {


    private List<Worker> workers;


    public WorkerService() {
        this.workers = new ArrayList<>(8);
        workers.add(new Worker("robin", 33));
        workers.add(new Worker("raccoon", 23));
        workers.add(new Worker("tom", 16));
        workers.add(new Worker("jerry", 16));
    }


    public Worker get(int id) {
        System.out.println("---------------------------->>>> Service get");
        return workers.get(id - 1);
    }


    @PreDestroy
    public void preDestroy() {
        System.out.println("--------------------Pre Destroy");
    }


    @Override
    public void destroy() throws Exception {
        System.out.println("--------------------Destroy");
    }
}
