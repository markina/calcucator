package ru.compscicenter.java2014.calculator;

/**
 * Created by Markina Margarita on 05.10.14.
 */
public class Abs extends UnaryOperation {
  public Abs(Expression expression) {
    super(expression);
  }

  @Override
  protected String getString() {
    return ABS;
  }

  @Override
  public double result() {
    return Math.abs(expression.result());
  }
}
