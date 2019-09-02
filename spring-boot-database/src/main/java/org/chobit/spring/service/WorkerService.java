package org.chobit.spring.service;


import org.chobit.spring.entity.Worker;
import org.chobit.spring.service.mapper.WorkerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkerService {


    @Autowired
    private WorkerMapper workerMapper;


    public void create() {
        workerMapper.create();
    }


    public Worker get(int id) {
        System.out.println("------------------------" + id);
        return workerMapper.get(id);
    }

}
