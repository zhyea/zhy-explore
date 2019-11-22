package org.chobit.spring.service;

import org.chobit.spring.TestBase;
import org.chobit.spring.operator.ConditionalOperators;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class ConditionalOperatorsTest extends TestBase {

    @Autowired
    private ConditionalOperators operators;

    @Test
    public void ternary() {
        Assert.assertEquals("a", operators.getTernary());
    }

    @Test
    public void ternaryForNull() {
        Assert.assertEquals("zhyea", operators.getTernaryForNull());
    }

    @Test
    public void elvis() {
        Assert.assertEquals("zhyea", operators.getElvis());
    }

}
