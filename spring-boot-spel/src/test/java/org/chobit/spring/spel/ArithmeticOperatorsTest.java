package org.chobit.spring.spel;

import org.chobit.spring.TestBase;
import org.chobit.spring.operator.ArithmeticOperators;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ArithmeticOperatorsTest extends TestBase {

    @Autowired
    private ArithmeticOperators arithmeticOperator;

    @Test
    public void add() {
        Assert.assertEquals(20, arithmeticOperator.getAdd(), 0.00001);
    }

    @Test
    public void addString() {
        Assert.assertEquals("String1 string2", arithmeticOperator.getAddString());
    }

    @Test
    public void subtract() {
        Assert.assertEquals(19, arithmeticOperator.getSubtract(), 0.00001);
    }

    @Test
    public void multiply() {
        Assert.assertEquals(20, arithmeticOperator.getMultiply(), 0.00001);
    }

    @Test
    public void divide() {
        Assert.assertEquals(18, arithmeticOperator.getDivide(), 0.00001);
    }

    @Test
    public void divideAlphabetic() {
        Assert.assertEquals(18, arithmeticOperator.getDivideAlphabetic(), 0.00001);
    }

    @Test
    public void modulo() {
        Assert.assertEquals(7, arithmeticOperator.getModulo(), 0.00001);
    }

    @Test
    public void moduloAlphabetic() {
        Assert.assertEquals(7, arithmeticOperator.getModuloAlphabetic(), 0.00001);
    }

    @Test
    public void powerOf() {
        Assert.assertEquals(512, arithmeticOperator.getPowerOf(), 0.00001);
    }

    @Test
    public void brackets() {
        Assert.assertEquals(17, arithmeticOperator.getBrackets(), 0.00001);
    }
}
