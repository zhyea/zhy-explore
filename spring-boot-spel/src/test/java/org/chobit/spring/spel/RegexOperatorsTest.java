package org.chobit.spring.spel;

import org.chobit.spring.TestBase;
import org.chobit.spring.operator.RegexOperators;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class RegexOperatorsTest extends TestBase {


    @Autowired
    private RegexOperators operators;


    @Test
    public void validNumericStringResult() {
        Assert.assertTrue(operators.isValidNumericValue());
    }

    @Test
    public void invalidNumericStringResult() {
        Assert.assertFalse(operators.isInvalidAlphabeticStringResult());
    }


    @Test
    public void validAlphabeticStringResult() {
        Assert.assertTrue(operators.isValidAlphabeticStringResult());
    }

    @Test
    public void invalidAlphabeticStringResult() {
        Assert.assertFalse(operators.isInvalidAlphabeticStringResult());
    }


    @Test
    public void validNumericValue() {
        Assert.assertTrue(operators.isValidNumericValue());
    }


}
