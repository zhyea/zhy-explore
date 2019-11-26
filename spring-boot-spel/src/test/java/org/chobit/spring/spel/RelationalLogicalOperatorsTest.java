package org.chobit.spring.spel;

import org.chobit.spring.TestBase;
import org.chobit.spring.operator.RelationalLogicalOperators;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class RelationalLogicalOperatorsTest extends TestBase {

    @Autowired
    private RelationalLogicalOperators operators;

    @Test
    public void equal() {
        Assert.assertTrue(operators.isEqual());
    }

    @Test
    public void equalAlphabetic() {
        Assert.assertTrue(operators.isEqualAlphabetic());
    }

    @Test
    public void notEqual() {
        Assert.assertFalse(operators.isNotEqual());
    }

    @Test
    public void notEqualAlphabetic() {
        Assert.assertFalse(operators.isNotEqualAlphabetic());
    }

    @Test
    public void lessThan() {
        Assert.assertFalse(operators.isLessThan());

    }

    @Test
    public void lessThanAlphabetic() {
        Assert.assertFalse(operators.isLessThanAlphabetic());
    }

    @Test
    public void lessThanOrEqual() {
        Assert.assertTrue(operators.isLessThanOrEqual());
    }

    @Test
    public void lessThanOrEqualAlphabetic() {
        Assert.assertTrue(operators.isLessThanOrEqualAlphabetic());
    }

    @Test
    public void greaterThan() {
        Assert.assertFalse(operators.isGreaterThan());
    }

    @Test
    public void greaterThanAlphabetic() {
        Assert.assertFalse(operators.isGreaterThanAlphabetic());
    }

    @Test
    public void greaterThanOrEqual() {
        Assert.assertTrue(operators.isGreaterThanOrEqual());
    }

    @Test
    public void greaterThanOrEqualAlphabetic() {
        Assert.assertTrue(operators.isGreaterThanOrEqualAlphabetic());
    }

}
