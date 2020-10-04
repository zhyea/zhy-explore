package org.chobit.spring.component;

import org.chobit.spring.ext.FormatterType;
import org.springframework.stereotype.Component;


@FormatterType("Bar")
@Component("barFormatter")
public class BarFormatter implements Formatter {

    @Override
    public String format() {
        return "bar";
    }

}
