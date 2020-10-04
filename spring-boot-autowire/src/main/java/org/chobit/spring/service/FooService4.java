package org.chobit.spring.service;

import org.chobit.spring.component.Formatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class FooService4 {

    @Autowired
    @Qualifier("fooFormatter")
    private Formatter formatter;




    public String formatFoo() {
        return formatter.format();
    }

}
