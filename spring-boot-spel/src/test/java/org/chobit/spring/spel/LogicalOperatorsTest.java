package org.chobit.spring.spel;

import org.chobit.spring.TestBase;
import org.chobit.spring.operator.LogicalOperators;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class LogicalOperatorsTest extends TestBase {


    @Autowired
    private LogicalOperators operators;

    @Test
    public void and() {
        Assert.assertTrue(operators.isAnd());
    }

    @Test
    public void andAlphabetic() {
        Assert.assertTrue(operators.isAndAlphabetic());
    }

    @Test
    public void or() {
        Assert.assertTrue(operators.isOr());
    }

    @Test
    public void orAlphabetic() {
        Assert.assertTrue(operators.isOrAlphabetic());
    }

    @Test
    public void not() {
        Assert.assertFalse(operators.isNot());
    }

    @Test
    public void notAlphabetic() {
        Assert.assertFalse(operators.isNotAlphabetic());
    }

}
