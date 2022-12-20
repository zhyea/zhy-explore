package org.chobit.spring.service;


import org.chobit.spring.bean.Worker;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@CacheConfig(cacheNames = "master")
public class MasterService {


    private List<Worker> workers;


    public MasterService() {
        this.workers = new ArrayList<>(8);
        workers.add(new Worker("robin", 33));
        workers.add(new Worker("raccoon", 23));
        workers.add(new Worker("tom", 16));
        workers.add(new Worker("HanMeimei", 16));
    }

    @Cacheable(key = "'get' + #id")
    public Worker get(int id) {
        System.out.println("------------------------" + id);
        return workers.get(id - 1);
    }

}
