package org.chobit.spring.controller;

import org.chobit.spring.bean.Worker;
import org.chobit.spring.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Order
@RestController
@RequestMapping("/worker")
@Import(WorkerService.class)
public class WorkerController {


    @Autowired
    private WorkerService service;


    @GetMapping("/{id}")
    public Worker get(@PathVariable("id") int id) {
        System.out.println("============================>>>> Controller get");
        return service.get(id);
    }

}
