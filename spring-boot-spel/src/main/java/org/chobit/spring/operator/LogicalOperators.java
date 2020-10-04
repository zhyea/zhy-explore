package org.chobit.spring.operator;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LogicalOperators {

    @Value("#{250 > 200 && 200 < 4000}") // true
    private boolean and;

    @Value("#{250 > 200 and 200 < 4000}") // true
    private boolean andAlphabetic;

    @Value("#{400 > 300 || 150 < 100}") // true
    private boolean or;

    @Value("#{400 > 300 or 150 < 100}") // true
    private boolean orAlphabetic;

    @Value("#{!true}") // false
    private boolean not;

    @Value("#{not true}") // false
    private boolean notAlphabetic;

    public boolean isAnd() {
        return and;
    }

    public boolean isAndAlphabetic() {
        return andAlphabetic;
    }

    public boolean isOr() {
        return or;
    }

    public boolean isOrAlphabetic() {
        return orAlphabetic;
    }

    public boolean isNot() {
        return not;
    }

    public boolean isNotAlphabetic() {
        return notAlphabetic;
    }
}
