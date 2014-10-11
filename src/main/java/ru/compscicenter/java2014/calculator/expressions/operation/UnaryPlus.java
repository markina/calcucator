package ru.compscicenter.java2014.calculator.expressions.operation;

import ru.compscicenter.java2014.calculator.Const;
import ru.compscicenter.java2014.calculator.expressions.Expression;
import ru.compscicenter.java2014.calculator.expressions.UnaryOperation;

/**
 * Created by Markina Margarita on 10.10.14.
 */
public class UnaryPlus extends UnaryOperation {
  public UnaryPlus(Expression expression) {
    super(expression);
  }

  @Override
  public String getString() {
    return Character.toString(Const.PLUS);
  }

  @Override
  public double result() {
    return expression.result();
  }
}
