package org.chobit.spring.service;


import org.chobit.spring.bean.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class WorkerService {


    @Value("#{'token:' + '${custom.token}'}")
    private String token;

    @Autowired
    private Worker worker;


    public String getName() {
        System.out.println("------------------------");
        return worker.getName();
    }


    public String spelToken() {
        return token;
    }

}
