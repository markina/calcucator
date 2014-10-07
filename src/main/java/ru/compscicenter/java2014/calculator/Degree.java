package ru.compscicenter.java2014.calculator;

/**
 * Created by Markina Margarita on 05.10.14.
 */
public class Degree extends BinaryOperation {
  public Degree(Expression left, Expression right) {
    super(left, right);
  }

  @Override
  public double result() {
    double l = left.result();
    double r = right.result();
    if(r >= 0) {
      return Math.pow(left.result(), right.result());
    } else {
      return 1.0/(Math.pow(l, (-1)*r));
    }
  }

  @Override
  protected String getString() {
    return "^";
  }
}
