package ru.compscicenter.java2014.calculator.expressions;

import ru.compscicenter.java2014.calculator.Const;

/**
 * Created by Markina Margarita on 05.10.14.
 */

public abstract class UnaryOperation extends Expression {

  protected Expression expression;

  public UnaryOperation(Expression expression) {
    this.expression = expression;
  }

  protected abstract String getString();

  @Override
  public String toString() {
    return getString() + Const.LEFT_BRACKET + expression.toString() + Const.RIGHT_BRACKET;
  }


}
