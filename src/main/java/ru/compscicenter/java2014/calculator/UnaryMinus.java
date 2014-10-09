package ru.compscicenter.java2014.calculator;

/**
 * Created by Markina Margarita on 08.10.14.
 */
public class UnaryMinus extends UnaryOperation {
  public UnaryMinus(Expression exp) {
    super(exp);
  }

  @Override
  protected String getString() {
    return "-" + e;
  }

  @Override
  public double result() {
    return -e.result();
  }
}
