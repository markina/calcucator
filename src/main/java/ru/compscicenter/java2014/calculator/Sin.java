package ru.compscicenter.java2014.calculator;

/**
 * Created by Markina Margarita on 05.10.14.
 */
public class Sin extends UnaryOperation {
  public Sin(Expression expression) {
    super(expression);
  }

  @Override
  protected String getString() {
    return SIN;
  }

  @Override
  public double result() {
    return Math.sin(expression.result());
  }
}
