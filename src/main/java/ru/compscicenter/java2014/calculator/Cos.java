package ru.compscicenter.java2014.calculator;

/**
 * Created by Markina Margarita on 05.10.14.
 */
public class Cos extends UnaryOperation {
  public Cos(Expression exp) {
    super(exp);
  }

  @Override
  protected String getString() {
    return "cos";
  }

  @Override
  public double result() {
    return Math.cos(e.result());
  }

  @Override
  public String toString() {
    return "cos(" + e.toString() + ")";
  }
}
