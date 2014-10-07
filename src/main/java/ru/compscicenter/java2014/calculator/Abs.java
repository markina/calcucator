package ru.compscicenter.java2014.calculator;

/**
 * Created by Markina Margarita on 05.10.14.
 */
public class Abs extends UnaryOperation {
  public Abs(Expression exp) {
    super(exp);
  }

  @Override
  protected String getString() {
    return "abs(" + e.toString() + ")";
  }

  @Override
  public double result() {
    return Math.abs(e.result());
  }
}
