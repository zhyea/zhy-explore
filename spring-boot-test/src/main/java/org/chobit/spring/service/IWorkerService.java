package org.chobit.spring.service;

import org.chobit.spring.bean.Worker;

import java.util.Optional;

public interface IWorkerService {

    
    Worker get(int id);


    Optional<Worker> getByName(String name);


    void add(Worker worker);

}
