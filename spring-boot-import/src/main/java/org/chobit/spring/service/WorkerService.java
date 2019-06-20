package org.chobit.spring.service;


import org.chobit.spring.bean.Worker;
import org.chobit.spring.tools.WorkerBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


public class WorkerService {

    @Autowired
    private WorkerBeanFactory factory;

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
