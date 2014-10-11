package ru.compscicenter.java2014.calculator.expressions;

import ru.compscicenter.java2014.calculator.Const;

/**
 * Created by Markina Margarita on 05.10.14.
 */

public abstract class BinaryOperation extends Expression {
  protected Expression left, right;

  public BinaryOperation(Expression left, Expression right) {
    this.left = left;
    this.right = right;
  }

  @Override
  public String toString() {
    return Const.LEFT_BRACKET + left.toString() + getString() + right.toString() + Const.RIGHT_BRACKET;
  }

  public abstract double result();

  public abstract char getString();

}
