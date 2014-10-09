package ru.compscicenter.java2014.calculator;

/**
 * Created by Markina Margarita on 05.10.14.
 */

/**
 * Exp <- Sum
 * Sum <- Product | Exp (('+' | '-') Exp)*
 * Product <- Degree | Exp (('*' | '/') Exp)*
 * Degree <- '-' Exp | '+' Exp | Value | Exp ('^' Exp)*
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
        if (isPlusOrMinus(s.charAt(i))) {
          if (isValueWithE(i, s)) {
            continue;
          }
          leftString = s.substring(0, i);
          rightString = s.substring(i + 1, s.length());
          if (i != 0 && !isPlusOrMinus(leftString.charAt(leftString.length() - 1))) {
            if (s.charAt(i) == '+') {
              exp = new Plus(parseExp(leftString), parseExp(rightString));
            } else {
              exp = new Minus(parseExp(leftString), parseExp(rightString));
            }
            return exp;
          }
        }

      }
    }

    exp = parseProduct(s);
    return exp;
  }

  private static Expression parseProduct(String s) {
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
        if (isMultiplicationOrDivision(s.charAt(i))) {
          leftString = s.substring(0, i);
          rightString = s.substring(i + 1, s.length());
          if (s.charAt(i) == '*') {
            exp = new Multiplication(parseExp(leftString), parseExp(rightString));
          } else {
            exp = new Division(parseExp(leftString), parseExp(rightString));
          }
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

    if (s.length() > 0 && s.charAt(0) == '+') {
      return parseExp(s.substring(1, s.length()));
    }

    if (s.length() > 0 && s.charAt(0) == '-') {
      return new UnaryMinus(parseExp(s.substring(1, s.length())));
    }


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
          exp = new Power(parseExp(leftString), parseExp(rightString));
          return exp;
        }

      }
    }
    exp = parseValue(s);
    return exp;
  }

  private static Expression parseValue(String s) {
    if (s.length() > 0 && s.charAt(0) == '(' && s.charAt(s.length() - 1) == ')') {
      return parseExp(s.substring(1, s.length() - 1));
    }

    if (s.length() >= 3) {
      String first3Chars = s.substring(0, 3);

      if (first3Chars.equalsIgnoreCase("cos")) {
        return new Cos(parseExp(s.substring(3, s.length())));
      }

      if (first3Chars.equalsIgnoreCase("sin")) {
        return new Sin(parseExp(s.substring(3, s.length())));
      }

      if (first3Chars.equalsIgnoreCase("abs")) {
        return new Abs(parseExp(s.substring(3, s.length())));
      }
    }
    return new Value(s);
  }

  private static boolean isPlusOrMinus(char c) {
    return c == '+' || c == '-';
  }

  private static boolean isMultiplicationOrDivision(char c) {
    return c == '*' || c == '/';
  }

  private static boolean isValueWithE(int position, String s) {
    return position != 0 && isE(s.charAt(position - 1));
  }

  private static boolean isE(char c) {
    return c == 'E' || c == 'e';
  }
}
