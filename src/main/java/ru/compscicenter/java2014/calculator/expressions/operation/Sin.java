package ru.compscicenter.java2014.calculator.expressions.operation;

import ru.compscicenter.java2014.calculator.Const;
import ru.compscicenter.java2014.calculator.expressions.Expression;
import ru.compscicenter.java2014.calculator.expressions.UnaryOperation;

/**
 * Created by Markina Margarita on 05.10.14.
 */
public class Sin extends UnaryOperation {
  public Sin(Expression expression) {
    super(expression);
  }

  @Override
  public String getString() {
    return Const.SIN;
  }

  @Override
  public double result() {
    return Math.sin(expression.result());
  }
}
