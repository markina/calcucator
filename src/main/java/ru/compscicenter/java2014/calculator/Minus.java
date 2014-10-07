package ru.compscicenter.java2014.calculator;

/**
 * Created by Markina Margarita on 05.10.14.
 */
public class Minus extends BinaryOperation {
  public Minus(Expression left, Expression right) {
    super(left, right);
  }

  @Override
  public double result() {
    return left.result() - right.result();
  }

  @Override
  protected String getString() {
    return "-";
  }


}
