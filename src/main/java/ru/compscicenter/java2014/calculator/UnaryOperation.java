package ru.compscicenter.java2014.calculator;

/**
 * Created by Markina Margarita on 05.10.14.
 */

public abstract class UnaryOperation extends Expression {

  protected Expression e;

  public UnaryOperation(Expression e) {
    this.e = e;
  }

  protected abstract String getString();

  @Override
  public String toString() {
    return getString();
  }


}
