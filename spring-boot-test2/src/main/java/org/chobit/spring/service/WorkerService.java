package org.chobit.spring.service;


import org.chobit.spring.bean.Worker;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@CacheConfig(cacheNames = "worker")
public class WorkerService {


    private List<Worker> workers;


    public WorkerService() {
        this.workers = new ArrayList<>(8);
        workers.add(new Worker("robin", 33));
        workers.add(new Worker("raccoon", 23));
        workers.add(new Worker("tom", 16));
        workers.add(new Worker("LiLei", 16));
    }


    public Worker get(int id) {
        return workers.get(id - 1);
    }

}
