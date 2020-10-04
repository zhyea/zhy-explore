package org.chobit.spring.service;

import org.chobit.spring.component.Formatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FooService6 {


    @Autowired
    private Formatter fooFormatter;


    public String formatFoo() {
        return fooFormatter.format();
    }
}
