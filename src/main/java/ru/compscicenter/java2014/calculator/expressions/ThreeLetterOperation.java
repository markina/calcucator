package ru.compscicenter.java2014.calculator.expressions;

/**
 * Created by Markina Margarita on 10.10.14.
 */
public abstract class ThreeLetterOperation extends UnaryOperation {
  public ThreeLetterOperation(Expression expression) {
    super(expression);
  }

  public static String getImportantString(String expressionString) {
    return expressionString.substring(3, expressionString.length());
  }
}
