package ru.compscicenter.java2014.calculator;

/**
 * Created by Markina Margarita on 05.10.14.
 */
public class Plus extends BinaryOperation {
  public Plus(Expression left, Expression right) {
    super(left, right);
  }

  @Override
  protected char getString() {
    return PLUS;
  }

  @Override
  public double result() {
    return left.result() + right.result();
  }
}
