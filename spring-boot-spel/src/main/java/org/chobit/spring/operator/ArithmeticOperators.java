package org.chobit.spring.operator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ArithmeticOperators {

    @Value("#{19 + 1}") // 20
    private double add;

    @Value("#{'String1 ' + 'string2'}") // "String1 string2"
    private String addString;

    @Value("#{20 - 1}") // 19
    private double subtract;

    @Value("#{10 * 2}") // 20
    private double multiply;

    @Value("#{36 / 2}") // 18
    private double divide;

    @Value("#{36 div 2}") // 18, the same as for / operator
    private double divideAlphabetic;

    @Value("#{37 % 10}") // 7
    private double modulo;

    @Value("#{37 mod 10}") // 7, the same as for % operator
    private double moduloAlphabetic;

    @Value("#{2 ^ 9}") // 512
    private double powerOf;

    @Value("#{(2 + 2) * 2 + 9}") // 17
    private double brackets;


    public double getAdd() {
        return add;
    }

    public String getAddString() {
        return addString;
    }

    public double getSubtract() {
        return subtract;
    }

    public double getMultiply() {
        return multiply;
    }

    public double getDivide() {
        return divide;
    }

    public double getDivideAlphabetic() {
        return divideAlphabetic;
    }

    public double getModulo() {
        return modulo;
    }

    public double getModuloAlphabetic() {
        return moduloAlphabetic;
    }

    public double getPowerOf() {
        return powerOf;
    }

    public double getBrackets() {
        return brackets;
    }
}
