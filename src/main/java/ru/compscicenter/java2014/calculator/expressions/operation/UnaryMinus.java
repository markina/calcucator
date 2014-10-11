package ru.compscicenter.java2014.calculator.expressions.operation;

import ru.compscicenter.java2014.calculator.Const;
import ru.compscicenter.java2014.calculator.expressions.Expression;
import ru.compscicenter.java2014.calculator.expressions.UnaryOperation;

/**
 * Created by Markina Margarita on 08.10.14.
 */
public class UnaryMinus extends UnaryOperation {
  public UnaryMinus(Expression expression) {
    super(expression);
  }

  @Override
  public String getString() {
    return Character.toString(Const.MINUS);
  }

  @Override
  public double result() {
    return -expression.result();
  }
}
