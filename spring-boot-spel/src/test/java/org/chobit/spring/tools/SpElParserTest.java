package org.chobit.spring.tools;

import org.junit.Assert;
import org.junit.Test;

import static org.chobit.spring.tools.SpElParser.parse;

public class SpElParserTest {


    @Test
    public void parseSimpleExpr() {
        String expr = "'zhyea'";
        String r = parse(expr);

        Assert.assertEquals("zhyea", r);
    }


    @Test
    public void parseExprWithMethod() {
        String expr = "'zhyea'.length()";
        int l = parse(expr);

        Assert.assertEquals(5, l);
    }


    @Test
    public void parseExprWithConstructor() {
        String expr = "new String('chobit').length()";
        int l = parse(expr);

        Assert.assertEquals(6, l);
    }


    @Test
    public void parseExprWithProp() {
        String expr = "'zhyea'.bytes";
        byte[] r = parse(expr);

        Assert.assertArrayEquals("zhyea".getBytes(), r);
    }

    @Test
    public void parseWithGenericType() {
        String expr = "'zhyea'.length()";
        int l = parse(expr, Integer.class);

        Assert.assertEquals(5, l);
    }

}
