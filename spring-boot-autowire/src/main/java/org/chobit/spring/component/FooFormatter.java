package org.chobit.spring.component;


import org.chobit.spring.ext.FormatterType;
import org.springframework.stereotype.Component;


@FormatterType("Foo")
@Component("fooFormatter")
public class FooFormatter implements Formatter {


    public String format() {
        return "foo";
    }


}
