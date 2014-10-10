package ru.compscicenter.java2014.calculator;

/**
 * Created by Markina Margarita on 05.10.14.
 */

import ru.compscicenter.java2014.calculator.expressions.*;
import ru.compscicenter.java2014.calculator.expressions.operation.*;

/**
 * Expression <- Sum
 * Sum <- Product | Exp (('+' | '-') Exp)*
 * Product <- Power | Exp (('*' | '/') Exp)*
 * Power <- '-' Exp | '+' Exp | Value | Exp ('^' Exp)*
 * Value <- [0-9E+-]+ | '(' Exp ')' | 'cos(' Exp ') | 'sin(' Exp ') | 'abs(' Exp ')
 */

public class Parser {

  public static Expression parseExpression(String expressionString) {
    Expression expression;
    int balance = 0;

    for (int i = expressionString.length() - 1; i >= 0; i--) {
      balance = updateBalance(expressionString, i, balance);

      if (balance == 0) {
        expression = getExpressionPlusOrMinusOrNull(expressionString, i);
        if (isNotNull(expression)) {
          return expression;
        }
      }
    }
    expression = parseProduct(expressionString);
    return expression;
  }

  private static Expression parseProduct(String expressionString) {
    Expression expression;
    int balance = 0;

    for (int i = expressionString.length() - 1; i >= 0; i--) {
      balance = updateBalance(expressionString, i, balance);

      if (balance == 0) {
        expression = getExpressionMultiplicationOrDivisionOrNull(expressionString, i);
        if (isNotNull(expression)) {
          return expression;
        }
      }
    }
    expression = parsePower(expressionString);
    return expression;
  }

  private static Expression parsePower(String expressionString) {
    Expression expression;
    expression = getExpressionUnaryPlusOrUnaryMinusOrNull(expressionString);
    if (isNotNull(expression)) {
      return expression;
    }

    int balance = 0;
    for (int i = 0; i < expressionString.length(); i++) {
      balance = updateBalance(expressionString, i, balance);

      if (balance == 0) {
        expression = getExpressionPowerOrNull(expressionString, i);
        if (isNotNull(expression)) {
          return expression;
        }
      }
    }
    expression = parseValue(expressionString);
    return expression;
  }


  private static Expression parseValue(String expressionString) {
    Expression expression;
    expression = getExpressionWithoutBracketsSidesOrNull(expressionString);
    if (isNotNull(expression)) {
      return expression;
    }

    expression = getExpressionCosOrSinOrAbsOrNull(expressionString);
    if (isNotNull(expression)) {
      return expression;
    }

    return new Value(expressionString);
  }

  private static int updateBalance(String expressionString, int position, int balance) {
    if (expressionString.charAt(position) == Const.LEFT_BRACKET) {
      balance++;
    }
    if (expressionString.charAt(position) == Const.RIGHT_BRACKET) {
      balance--;
    }
    return balance;
  }

  private static Expression getExpressionMultiplicationOrDivisionOrNull(String expressionString, int position) {
    if (isMultiplicationOrDivision(expressionString.charAt(position))) {

      String leftString = BinaryOperation.getLeftImportantString(position, expressionString);
      String rightString = BinaryOperation.getRightImportantString(position, expressionString);

      if (expressionString.charAt(position) == Const.MULTIPLICATION) {
        return new Multiplication(parseExpression(leftString), parseExpression(rightString));
      } else {
        return new Division(parseExpression(leftString), parseExpression(rightString));
      }
    }
    return null;
  }

  private static Expression getExpressionPowerOrNull(String expressionString, int position) {
    if (expressionString.charAt(position) == Const.POWER) {

      String leftString = BinaryOperation.getLeftImportantString(position, expressionString);
      String rightString = BinaryOperation.getRightImportantString(position, expressionString);

      return new Power(parseExpression(leftString), parseExpression(rightString));
    }
    return null;
  }

  private static Expression getExpressionUnaryPlusOrUnaryMinusOrNull(String expressionString) {
    if (expressionString.length() > 0) {
      if (expressionString.charAt(0) == Const.PLUS) {
        return new UnaryPlus(parseExpression(SignOperation.getImportantString(expressionString)));
      }

      if (expressionString.charAt(0) == Const.MINUS) {
        return new UnaryMinus(parseExpression(SignOperation.getImportantString(expressionString)));
      }
    }
    return null;
  }

  private static Expression getExpressionCosOrSinOrAbsOrNull(String expressionString) {
    if (expressionString.length() >= 3) {

      String first3Chars = expressionString.substring(0, 3);
      String importantString = ThreeLetterOperation.getImportantString(expressionString);

      if (isCos(first3Chars)) {
        return new Cos(parseExpression(importantString));
      }

      if (isSin(first3Chars)) {
        return new Sin(parseExpression(importantString));
      }

      if (isAbs(first3Chars)) {
        return new Abs(parseExpression(importantString));
      }
    }
    return null;
  }

  private static Expression getExpressionPlusOrMinusOrNull(String expressionString, int positionOperation) {
    if (isPlusOrMinus(expressionString.charAt(positionOperation))) {
      if (isValueWithE(positionOperation, expressionString)) {
        return null;
      }

      String leftString = BinaryOperation.getLeftImportantString(positionOperation, expressionString);
      String rightString = BinaryOperation.getRightImportantString(positionOperation, expressionString);

      if (isLastSymbolArithmeticSign(leftString)) {
        if (expressionString.charAt(positionOperation) == Const.PLUS) {
          return new Plus(parseExpression(leftString), parseExpression(rightString));
        } else {
          return new Minus(parseExpression(leftString), parseExpression(rightString));
        }
      }
    }
    return null;
  }

  private static Expression getExpressionWithoutBracketsSidesOrNull(String expressionString) {
    if (isExpressionInBracketsSides(expressionString)) {
      String importantString = expressionString.substring(1, expressionString.length() - 1);
      return parseExpression(importantString);
    }
    return null;
  }

  private static boolean isLastSymbolArithmeticSign(String leftString) {
    return leftString.length() != 0 && !isArithmeticSign(leftString.charAt(leftString.length() - 1));
  }

  private static boolean isNotNull(Expression expression) {
    return expression != null;
  }

  private static boolean isExpressionInBracketsSides(String expressionString) {
    return expressionString.length() > 0 && expressionString.charAt(0) == Const.LEFT_BRACKET && expressionString.charAt(expressionString.length() - 1) == Const.RIGHT_BRACKET;
  }

  private static boolean isAbs(String s) {
    return s.equalsIgnoreCase(Const.ABS);
  }

  private static boolean isSin(String s) {
    return s.equalsIgnoreCase(Const.SIN);
  }

  private static boolean isCos(String s) {
    return s.equalsIgnoreCase(Const.COS);
  }

  private static boolean isPlusOrMinus(char c) {
    return c == Const.PLUS || c == Const.MINUS;
  }

  private static boolean isMultiplicationOrDivision(char c) {
    return c == Const.MULTIPLICATION || c == Const.DIVISION;
  }

  private static boolean isValueWithE(int position, String s) {
    return position != 0 && isE(s.charAt(position - 1));
  }

  private static boolean isE(char c) {
    return c == Const.SMALL_E || c == Const.BIG_E;
  }

  private static boolean isArithmeticSign(char c) {
    return c == Const.POWER
            || c == Const.MULTIPLICATION
            || c == Const.DIVISION
            || c == Const.PLUS
            || c == Const.MINUS;
  }

}
