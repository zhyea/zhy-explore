package org.chobit.spring.service;


import org.chobit.spring.entity.Worker;
import org.chobit.spring.service.mapper.WorkerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author robin
 */
@Service
public class WorkerService {


    @Autowired
    private WorkerMapper mapper;


    public void create() {
        mapper.create();
    }


    public boolean insert(String name) {
        Worker worker = new Worker();
        worker.setName(name);
        return mapper.insert(worker);
    }

    public Worker get(int id) {
        System.out.println("------------------------" + id);
        return mapper.get(id);
    }

}
