package org.chobit.spring.tools;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class SpElParser {


    public static <T> T parse(String expr) {
        ExpressionParser expressionParser = new SpelExpressionParser();
        Expression expression = expressionParser.parseExpression(expr);
        return (T) expression.getValue();
    }


    public static <T> T parse(String expr, Class<T> clazz) {
        ExpressionParser expressionParser = new SpelExpressionParser();
        Expression expression = expressionParser.parseExpression(expr);
        return expression.getValue(clazz);
    }


    private SpElParser() {
    }

}
