package org.chobit.spring.app1.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/api")
public class HelloController {


    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @ResponseBody
    @GetMapping("/hello/{name}")
    public String hello(@PathVariable("name") String name) {
        String s = String.format("hello %s!", name);
        logger.info(s);
        return s;
    }


}
