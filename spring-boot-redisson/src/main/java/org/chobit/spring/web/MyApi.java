package org.chobit.spring.web;


import org.chobit.spring.service.MyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author rui.zhang
 */
@RestController
@RequestMapping(value = "/api/zhyea")
public class MyApi {


    @Resource
    private MyService myService;


    @GetMapping("/{id}")
    public int get(@PathVariable("id") int id) {
        System.out.println(1);
        myService.execute();
        return id;
    }

}