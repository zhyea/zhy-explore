package org.chobit.spring.service;


import org.chobit.spring.bean.Worker;
import org.chobit.spring.exception.NonExistingWorkerException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WorkerServiceImpl implements IWorkerService, DisposableBean {


    private List<Worker> workers;


    public WorkerServiceImpl() {
        this.workers = new ArrayList<>(8);
        workers.add(new Worker("robin", 33));
        workers.add(new Worker("raccoon", 23));
        workers.add(new Worker("tom", 16));
        workers.add(new Worker("HanMeimei", 16));
    }


    public Worker get(int id) {
        System.out.println("---------------------------->>>> Service get");
        if (id > workers.size()) {
            throw new NonExistingWorkerException();
        }
        return workers.get(id - 1);
    }


    public Optional<Worker> getByName(String name) {
        System.out.println("---------------------------->>>> Service getByName");
        return workers.stream().filter(e -> e.getName().equals(name)).findAny();
    }


    public void add(Worker worker) {
        System.out.println("---------------------------->>>> Service add");
        workers.add(worker);
    }


    @PreDestroy
    public void preDestroy(){
        System.out.println("--------------------Pre Destroy");
    }


    @Override
    public void destroy() throws Exception {
        System.out.println("--------------------Destroy");
    }
}
