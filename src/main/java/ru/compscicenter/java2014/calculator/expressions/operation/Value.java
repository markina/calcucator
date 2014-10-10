package ru.compscicenter.java2014.calculator.expressions.operation;

import ru.compscicenter.java2014.calculator.expressions.Expression;

/**
 * Created by Markina Margarita on 05.10.14.
 */

public class Value extends Expression {
  double value;

  public Value(String string) {
    if (string.length() == 0) {
      value = (double) 0;
    } else {
      value = Double.parseDouble(string);
    }
  }

  @Override
  public double result() {
    return value;
  }

  @Override
  public String toString() {
    return ((Double) value).toString();
  }
}
