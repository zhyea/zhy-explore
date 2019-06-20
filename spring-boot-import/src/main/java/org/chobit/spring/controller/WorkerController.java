package org.chobit.spring.controller;

import org.chobit.spring.bean.Worker;
import org.chobit.spring.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Order
@RestController
@RequestMapping("/worker")
@Import(WorkerService.class)
public class WorkerController {


    @Autowired
    private WorkerService service;


    @GetMapping("/all")
    public List<Worker> all(){
        return service.allWorkers();
    }


}
