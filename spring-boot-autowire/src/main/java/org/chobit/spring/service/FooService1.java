package org.chobit.spring.service;

import org.chobit.spring.component.FooFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FooService1 {


    @Autowired
    private FooFormatter fooFormatter;

    public String formatFoo() {
        return fooFormatter.format();
    }


}
