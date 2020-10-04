package org.chobit.spring.service;

import org.chobit.spring.component.FooFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FooService3 {


    private FooFormatter fooFormatter;


    @Autowired
    public FooService3(FooFormatter fooFormatter) {
        this.fooFormatter = fooFormatter;
    }


    public String formatFoo() {
        return fooFormatter.format();
    }


}
