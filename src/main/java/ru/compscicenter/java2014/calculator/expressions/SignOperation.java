package ru.compscicenter.java2014.calculator.expressions;

/**
 * Created by Markina Margarita on 10.10.14.
 */
public abstract class SignOperation extends UnaryOperation {
  public SignOperation(Expression expression) {
    super(expression);
  }

  public static String getImportantString(String expressionString) {
    return expressionString.substring(1, expressionString.length());
  }
}
