package ru.compscicenter.java2014.calculator;

/**
 * Created by Markina Margarita on 05.10.14.
 */
public class Sin extends UnaryOperation {
  public Sin(Expression exp) {
    super(exp);
  }

  @Override
  protected String getString() {
    return "sin";
  }

  @Override
  public double result() {
    return Math.sin(e.result());
  }
}
