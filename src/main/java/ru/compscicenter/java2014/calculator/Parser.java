package ru.compscicenter.java2014.calculator;

/**
 * Created by Markina Margarita on 05.10.14.
 */

/**
 * Exp <- Sum
 * Sum <- Product (('+' | '-') Product)*
 * Product <- Degree (('*' | '/') Degree)*
 * Degree <- Exp ('^' Exp)*
 * Value <- [0-9E+-]+ | '(' Exp ')' | 'cos(' Exp ') | 'sin(' Exp ') | 'abs(' Exp ')
 */


public class Parser {
  public static Expression parseExp(String s) {
    Expression exp;
    int bal = 0;
    String leftString;
    String rightString;
    for (int i = s.length() - 1; i >= 0; i--) {
      if (s.charAt(i) == '(') {
        bal++;
        continue;
      }
      if (s.charAt(i) == ')') {
        bal--;
        continue;
      }
      if (bal == 0) {
        if (s.charAt(i) == '+') {
          if (isValueWithE(i, s)) {
            continue;
          }
          leftString = s.substring(0, i);
          rightString = s.substring(i + 1, s.length());
          exp = new Plus(parseExp(leftString), parseExp(rightString));
          return exp;
        }
        if (s.charAt(i) == '-') {
          if (isValueWithE(i, s)) {
            continue;
          }
          leftString = s.substring(0, i);
          rightString = s.substring(i + 1, s.length());
          exp = new Minus(parseExp(leftString), parseExp(rightString));
          return exp;
        }
      }
    }

    exp = parseProduct(s);
    return exp;
  }

  private static boolean isValueWithE(int position, String s) {
    if (position != 0) {
      if (s.charAt(position - 1) == 'E' || s.charAt(position - 1) == 'e') {
        return true;
      }
    } else {
      return false;
    }
    return false;
  }

  public static Expression parseProduct(String s) {
    Expression exp;
    int bal = 0;
    String leftString;
    String rightString;
    for (int i = s.length() - 1; i >= 0; i--) {
      if (s.charAt(i) == '(') {
        bal++;
        continue;
      }
      if (s.charAt(i) == ')') {
        bal--;
        continue;
      }
      if (bal == 0) {
        if (s.charAt(i) == '*') {
          leftString = s.substring(0, i);
          rightString = s.substring(i + 1, s.length());
          exp = new Multiplication(parseDegree(leftString), parseDegree(rightString));
          return exp;
        }
        if (s.charAt(i) == '/') {
          leftString = s.substring(0, i);
          rightString = s.substring(i + 1, s.length());
          exp = new Division(parseDegree(leftString), parseDegree(rightString));
          return exp;
        }

      }
    }

    exp = parseDegree(s);
    return exp;

  }

  private static Expression parseDegree(String s) {
    Expression exp;
    int bal = 0;
    String leftString;
    String rightString;
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '(') {
        bal++;
        continue;
      }
      if (s.charAt(i) == ')') {
        bal--;
        continue;
      }
      if (bal == 0) {
        if (s.charAt(i) == '^') {
          leftString = s.substring(0, i);
          rightString = s.substring(i + 1, s.length());
          exp = new Degree(parseExp(leftString), parseExp(rightString));
          return exp;
        }

      }
    }
    exp = parseValue(s);
    return exp;
  }


  public static Expression parseValue(String s) {
    if (s.length() > 0 && s.charAt(0) == '(' && s.charAt(s.length() - 1) == ')') {
      return parseExp(s.substring(1, s.length() - 1));
    }


    if (s.length() >= 3 && s.substring(0, 3).equalsIgnoreCase("cos")) {
      return new Cos(parseExp(s.substring(3, s.length())));
    }

    if (s.length() >= 3 && s.substring(0, 3).equalsIgnoreCase("sin")) {
      return new Sin(parseExp(s.substring(3, s.length())));
    }

    if (s.length() >= 3 && s.substring(0, 3).equalsIgnoreCase("abs")) {
      return new Abs(parseExp(s.substring(3, s.length())));
    }

    return new Value(s);
  }
}
