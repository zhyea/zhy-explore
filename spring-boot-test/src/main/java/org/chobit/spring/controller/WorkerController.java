package org.chobit.spring.controller;

import org.chobit.spring.bean.Worker;
import org.chobit.spring.service.IWorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@Order
@RestController
@RequestMapping("/worker")
public class WorkerController {


    @Autowired
    private IWorkerService service;


    @GetMapping("/{id}")
    public Worker get(@PathVariable("id") int id) {
        System.out.println("============================>>>> Controller get");
        return service.get(id);
    }


    @GetMapping
    public Worker getByName(@RequestParam("name") String name) {
        System.out.println("============================>>>> Controller getByName");
        return service.getByName(name).orElseGet(() -> null);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody Worker worker) {
        System.out.println("============================>>>> Controller add");
        service.add(worker);
    }
}
