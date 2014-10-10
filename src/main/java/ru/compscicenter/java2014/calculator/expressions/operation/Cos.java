package ru.compscicenter.java2014.calculator.expressions.operation;

import ru.compscicenter.java2014.calculator.Const;
import ru.compscicenter.java2014.calculator.expressions.Expression;
import ru.compscicenter.java2014.calculator.expressions.ThreeLetterOperation;

/**
 * Created by Markina Margarita on 05.10.14.
 */
public class Cos extends ThreeLetterOperation {
  public Cos(Expression exp) {
    super(exp);
  }

  @Override
  protected String getString() {
    return Const.COS;
  }

  @Override
  public double result() {
    return Math.cos(expression.result());
  }

}
