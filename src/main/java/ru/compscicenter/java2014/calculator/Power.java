package ru.compscicenter.java2014.calculator;

/**
 * Created by Markina Margarita on 05.10.14.
 */
public class Power extends BinaryOperation {
  public Power(Expression left, Expression right) {
    super(left, right);
  }

  @Override
  public double result() {
    return Math.pow(left.result(), right.result());
  }

  @Override
  protected char getString() {
    return POWER;
  }
}
