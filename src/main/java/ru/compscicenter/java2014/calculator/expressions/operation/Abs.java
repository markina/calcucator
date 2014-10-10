package ru.compscicenter.java2014.calculator.expressions.operation;

import ru.compscicenter.java2014.calculator.Const;
import ru.compscicenter.java2014.calculator.expressions.Expression;
import ru.compscicenter.java2014.calculator.expressions.ThreeLetterOperation;

/**
 * Created by Markina Margarita on 05.10.14.
 */
public class Abs extends ThreeLetterOperation {
  public Abs(Expression expression) {
    super(expression);
  }

  @Override
  protected String getString() {
    return Const.ABS;
  }

  @Override
  public double result() {
    return Math.abs(expression.result());
  }
}