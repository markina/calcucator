package ru.compscicenter.java2014.calculator;

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
    return '(' + left.toString() + getString() + right.toString() + ')';
  }

  public abstract double result();

  protected abstract String getString();

}
