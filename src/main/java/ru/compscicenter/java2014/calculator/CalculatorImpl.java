package ru.compscicenter.java2014.calculator;

import ru.compscicenter.java2014.calculator.expressions.Expression;

/**
 * Created by Markina Margarita on 05.10.14.
 */

public class CalculatorImpl implements Calculator {
  public double calculate(String expression) {
    expression = expression.replaceAll("\\s+", "");
    Expression exp = Parser.parseExpression(expression);
    return exp.result();
  }

}
