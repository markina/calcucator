package ru.compscicenter.java2014.calculator.expressions.operation;

import ru.compscicenter.java2014.calculator.Const;
import ru.compscicenter.java2014.calculator.expressions.BinaryOperation;
import ru.compscicenter.java2014.calculator.expressions.Expression;

/**
 * Created by Markina Margarita on 05.10.14.
 */
public class Minus extends BinaryOperation {
  public Minus(Expression left, Expression right) {
    super(left, right);
  }

  @Override
  public double result() {
    return left.result() - right.result();
  }

  @Override
  public char getString() {
    return Const.MINUS;
  }


}
