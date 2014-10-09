package ru.compscicenter.java2014.calculator;

/**
 * Created by Markina Margarita on 08.10.14.
 */
public class UnaryMinus extends UnaryOperation {
  public UnaryMinus(Expression expression) {
    super(expression);
  }

  @Override
  protected String getString() {
    return "" + MINUS;
  }

  @Override
  public double result() {
    return -expression.result();
  }
}
