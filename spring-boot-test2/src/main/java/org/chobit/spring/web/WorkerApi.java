package org.chobit.spring.web;


import org.chobit.spring.bean.Worker;
import org.chobit.spring.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/worker")
public class WorkerApi {

    @Autowired
    private WorkerService workerService;

    @PostMapping
    public int add(@RequestBody Worker worker) {
        System.out.println("------>>> add worker: " + worker.getName());
        return 9;
    }


    @PutMapping
    public boolean update(@RequestBody Worker worker) {
        System.out.println("------>>> update worker: " + worker.getName());
        return true;
    }


    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable("id") int id) {
        System.out.println("------->>> delete worker: " + id);
        return true;
    }


    @GetMapping("/{id}")
    public Worker get(@PathVariable("id") int id) {
        System.out.println("------->>> get worker: " + id);
        return workerService.get(id);
    }

}
