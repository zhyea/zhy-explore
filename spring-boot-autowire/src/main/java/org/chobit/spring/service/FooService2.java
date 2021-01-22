package org.chobit.spring.service;


import org.chobit.spring.component.FooFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FooService2 {


    private FooFormatter fooFormatter;


    @Autowired
    public void setFooFormatter(FooFormatter fooFormatter) {
        this.fooFormatter = fooFormatter;
    }



    public String formatFoo() {
        return fooFormatter.format();
    }


}
