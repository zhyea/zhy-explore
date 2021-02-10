package org.chobit.spring.service;

import org.chobit.spring.entity.Worker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author robin
 */
@Service
public class MyService {


    private static final Logger logger = LoggerFactory.getLogger(MyService.class);

    @Autowired
    private WorkerService workerService;


    public void start() {
        try {
            workerService.create();
            workerService.insert("Tom", 16);

            TimeUnit.SECONDS.sleep(60);

            Worker worker = workerService.get(1);

            System.out.println(worker);
        } catch (Exception e) {
            logger.error("Service error.", e);
        }
    }


}
