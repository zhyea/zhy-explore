package org.chobit.spring.service;


import org.chobit.common.concurrent.Threads;
import org.chobit.common.utils.JsonKit;
import org.chobit.spring.entity.Worker;
import org.chobit.spring.service.mapper.WorkerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

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


    @Transactional(rollbackFor = Exception.class)
    public int insert(String name) {
        Worker worker = new Worker();
        worker.setName(name);
        mapper.insert(worker);
        return worker.getId();
    }


    @Transactional(rollbackFor = Exception.class)
    public boolean updateAge(int id, int age) {
        System.out.println("=======================>>>>> 开始更新年龄");
        Worker worker = this.get(id);
        System.out.println("========>>> updateAge: " + worker);
        Threads.sleep(TimeUnit.SECONDS, 1);
        return this.mapper.updateAge(id, age);
    }


    @Transactional(rollbackFor = Exception.class)
    public boolean updateName(int id, String name) {
        System.out.println("=======================>>>>> 开始更新姓名");
        Worker worker = this.get(id);
        System.out.println("========>>> updateName: " + worker);
        Threads.sleep(TimeUnit.SECONDS, 1);
        return this.mapper.updateName(id, name);
    }


    public Worker get(int id) {
        System.out.println("------------------------" + id);
        return mapper.get(id);
    }

}
