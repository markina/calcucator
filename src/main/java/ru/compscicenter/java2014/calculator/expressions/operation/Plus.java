package ru.compscicenter.java2014.calculator.expressions.operation;

import ru.compscicenter.java2014.calculator.Const;
import ru.compscicenter.java2014.calculator.expressions.BinaryOperation;
import ru.compscicenter.java2014.calculator.expressions.Expression;

/**
 * Created by Markina Margarita on 05.10.14.
 */
public class Plus extends BinaryOperation {
  public Plus(Expression left, Expression right) {
    super(left, right);
  }

  @Override
  public char getString() {
    return Const.PLUS;
  }

  @Override
  public double result() {
    return left.result() + right.result();
  }
}
