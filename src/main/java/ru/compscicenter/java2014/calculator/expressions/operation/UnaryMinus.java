package ru.compscicenter.java2014.calculator.expressions.operation;

import ru.compscicenter.java2014.calculator.Const;
import ru.compscicenter.java2014.calculator.expressions.Expression;
import ru.compscicenter.java2014.calculator.expressions.SignOperation;

/**
 * Created by Markina Margarita on 08.10.14.
 */
public class UnaryMinus extends SignOperation {
  public UnaryMinus(Expression expression) {
    super(expression);
  }

  @Override
  protected String getString() {
    return Character.toString(Const.MINUS);
  }

  @Override
  public double result() {
    return -expression.result();
  }
}
