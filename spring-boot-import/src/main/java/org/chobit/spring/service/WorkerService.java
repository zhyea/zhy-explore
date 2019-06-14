package org.chobit.spring.service;


import org.chobit.spring.bean.Worker;
import org.chobit.spring.tools.WorkerBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


public class WorkerService {


    private List<Worker> workers;

    @Autowired
    private WorkerBeanFactory factory;

    public WorkerService() {
        this.workers = new ArrayList<>(2);
        workers.add(new Worker("robin", 33));
        workers.add(new Worker("raccoon", 23));
    }


    public Worker get(int id) {
        System.out.println("---------------------------->>>> Service get");
        return workers.get(id - 1);
    }

    public List<Worker> allWorkers() {
        List<Worker> list = new ArrayList<>(4);
        list.add(factory.get("tom"));
        list.add(factory.get("anotherTom"));
        list.add(factory.get("selectTom"));
        list.add(factory.get("jerry"));

        System.out.println(list);

        return list;
    }

}
