package org.chobit.spring.service;

import org.chobit.spring.component.Formatter;
import org.chobit.spring.ext.FormatterType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FooService5 {


    @FormatterType("Foo")
    @Autowired
    private Formatter formatter;


    public String formatFoo() {
        return formatter.format();
    }
}
