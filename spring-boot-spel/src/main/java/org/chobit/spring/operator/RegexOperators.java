package org.chobit.spring.operator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RegexOperators {


    @Value("#{'100' matches '\\d+' }") // true
    private boolean validNumericStringResult;

    @Value("#{'100fghdjf' matches '\\d+' }") // false
    private boolean invalidNumericStringResult;

    @Value("#{'valid alphabetic string' matches '[a-zA-Z\\s]+' }") // true
    private boolean validAlphabeticStringResult;

    @Value("#{'invalid alphabetic string #$1' matches '[a-zA-Z\\s]+' }") // false
    private boolean invalidAlphabeticStringResult;

    @Value("#{worker.id matches '\\d+'}") // true if someValue contains only digits
    private boolean validNumericValue;


    public boolean isValidNumericStringResult() {
        return validNumericStringResult;
    }

    public void setValidNumericStringResult(boolean validNumericStringResult) {
        this.validNumericStringResult = validNumericStringResult;
    }

    public boolean isInvalidNumericStringResult() {
        return invalidNumericStringResult;
    }

    public void setInvalidNumericStringResult(boolean invalidNumericStringResult) {
        this.invalidNumericStringResult = invalidNumericStringResult;
    }

    public boolean isValidAlphabeticStringResult() {
        return validAlphabeticStringResult;
    }

    public void setValidAlphabeticStringResult(boolean validAlphabeticStringResult) {
        this.validAlphabeticStringResult = validAlphabeticStringResult;
    }

    public boolean isInvalidAlphabeticStringResult() {
        return invalidAlphabeticStringResult;
    }

    public void setInvalidAlphabeticStringResult(boolean invalidAlphabeticStringResult) {
        this.invalidAlphabeticStringResult = invalidAlphabeticStringResult;
    }

    public boolean isValidNumericValue() {
        return validNumericValue;
    }

    public void setValidNumericValue(boolean validNumericValue) {
        this.validNumericValue = validNumericValue;
    }
}
