package org.chobit.spring.operator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConditionalOperators {


    @Value("#{2 > 1 ? 'a' : 'b'}") // "a"
    private String ternary;


    @Value("#{worker.name != null ? worker.name : 'zhyea'}")
    private String ternaryForNull;

    @Value("#{worker.name ?: 'zhyea'}")
    private String elvis;


    public String getTernary() {
        return ternary;
    }

    public String getTernaryForNull() {
        return ternaryForNull;
    }

    public String getElvis() {
        return elvis;
    }
}
