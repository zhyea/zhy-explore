package org.chobit.spring.operator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RelationalLogicalOperators {


    @Value("#{1 == 1}") // true
    private boolean equal;

    @Value("#{1 eq 1}") // true
    private boolean equalAlphabetic;

    @Value("#{1 != 1}") // false
    private boolean notEqual;

    @Value("#{1 ne 1}") // false
    private boolean notEqualAlphabetic;

    @Value("#{1 < 1}") // false
    private boolean lessThan;

    @Value("#{1 lt 1}") // false
    private boolean lessThanAlphabetic;

    @Value("#{1 <= 1}") // true
    private boolean lessThanOrEqual;

    @Value("#{1 le 1}") // true
    private boolean lessThanOrEqualAlphabetic;

    @Value("#{1 > 1}") // false
    private boolean greaterThan;

    @Value("#{1 gt 1}") // false
    private boolean greaterThanAlphabetic;

    @Value("#{1 >= 1}") // true
    private boolean greaterThanOrEqual;

    @Value("#{1 ge 1}") // true
    private boolean greaterThanOrEqualAlphabetic;


    public boolean isEqual() {
        return equal;
    }

    public boolean isEqualAlphabetic() {
        return equalAlphabetic;
    }

    public boolean isNotEqual() {
        return notEqual;
    }

    public boolean isNotEqualAlphabetic() {
        return notEqualAlphabetic;
    }

    public boolean isLessThan() {
        return lessThan;
    }

    public boolean isLessThanAlphabetic() {
        return lessThanAlphabetic;
    }

    public boolean isLessThanOrEqual() {
        return lessThanOrEqual;
    }

    public boolean isLessThanOrEqualAlphabetic() {
        return lessThanOrEqualAlphabetic;
    }

    public boolean isGreaterThan() {
        return greaterThan;
    }

    public boolean isGreaterThanAlphabetic() {
        return greaterThanAlphabetic;
    }

    public boolean isGreaterThanOrEqual() {
        return greaterThanOrEqual;
    }

    public boolean isGreaterThanOrEqualAlphabetic() {
        return greaterThanOrEqualAlphabetic;
    }
}
