package org.chobit.spring.web;


import org.chobit.spring.autoconfigure.rw.ResponseWrapper;
import org.chobit.spring.model.UserLoginRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author rui.zhang
 */
@ResponseWrapper
@RestController
@RequestMapping("")
public class IndexController {


    @GetMapping("")
    public String index(@RequestParam("name") String name) {
        return "Hello " + name + "!";
    }


    @PostMapping("/user")
    public String user(@Validated @RequestBody UserLoginRequest req) {
        return req.getName();
    }

}
