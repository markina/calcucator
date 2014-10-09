package ru.compscicenter.java2014.calculator;

/**
 * Created by Markina Margarita on 05.10.14.
 */

/**
 * Expression <- Sum
 * Sum <- Product | Exp (('+' | '-') Exp)*
 * Product <- Power | Exp (('*' | '/') Exp)*
 * Power <- '-' Exp | '+' Exp | Value | Exp ('^' Exp)*
 * Value <- [0-9E+-]+ | '(' Exp ')' | 'cos(' Exp ') | 'sin(' Exp ') | 'abs(' Exp ')
 */


public class Parser extends Сonstants {

  public static Expression parseExpression(String s) {
    Expression expression;
    int bal = 0;
    String leftString;
    String rightString;


    for (int i = s.length() - 1; i >= 0; i--) {
      if (s.charAt(i) == LEFT_BRACKET) {
        bal++;
        continue;
      }
      if (s.charAt(i) == RIGHT_BRACKET) {
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
            if (s.charAt(i) == PLUS) {
              expression = new Plus(parseExpression(leftString), parseExpression(rightString));
            } else {
              expression = new Minus(parseExpression(leftString), parseExpression(rightString));
            }
            return expression;
          }
        }

      }
    }

    expression = parseProduct(s);
    return expression;
  }

  private static Expression parseProduct(String s) {
    Expression expression;
    int bal = 0;
    String leftString;
    String rightString;
    for (int i = s.length() - 1; i >= 0; i--) {
      if (s.charAt(i) == LEFT_BRACKET) {
        bal++;
        continue;
      }
      if (s.charAt(i) == RIGHT_BRACKET) {
        bal--;
        continue;
      }
      if (bal == 0) {
        if (isMultiplicationOrDivision(s.charAt(i))) {
          leftString = s.substring(0, i);
          rightString = s.substring(i + 1, s.length());
          if (s.charAt(i) == MULTIPLICATION) {
            expression = new Multiplication(parseExpression(leftString), parseExpression(rightString));
          } else {
            expression = new Division(parseExpression(leftString), parseExpression(rightString));
          }
          return expression;
        }

      }
    }

    expression = parsePower(s);
    return expression;

  }

  private static Expression parsePower(String s) {
    Expression expression;
    int bal = 0;
    String leftString;
    String rightString;

    if (s.length() > 0) {
      if (s.charAt(0) == PLUS) {
        return parseExpression(s.substring(1, s.length()));
      }

      if (s.charAt(0) == MINUS) {
        return new UnaryMinus(parseExpression(s.substring(1, s.length())));
      }
    }


    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == LEFT_BRACKET) {
        bal++;
        continue;
      }
      if (s.charAt(i) == RIGHT_BRACKET) {
        bal--;
        continue;
      }
      if (bal == 0) {
        if (s.charAt(i) == POWER) {
          leftString = s.substring(0, i);
          rightString = s.substring(i + 1, s.length());
          expression = new Power(parseExpression(leftString), parseExpression(rightString));
          return expression;
        }

      }
    }
    expression = parseValue(s);
    return expression;
  }

  private static Expression parseValue(String s) {
    if (s.length() > 0 && s.charAt(0) == LEFT_BRACKET && s.charAt(s.length() - 1) == RIGHT_BRACKET) {
      return parseExpression(s.substring(1, s.length() - 1));
    }

    if (s.length() >= 3) {
      String first3Chars = s.substring(0, 3);

      if (isCos(first3Chars)) {
        return new Cos(parseExpression(s.substring(3, s.length())));
      }

      if (isSin(first3Chars)) {
        return new Sin(parseExpression(s.substring(3, s.length())));
      }

      if (isAbs(first3Chars)) {
        return new Abs(parseExpression(s.substring(3, s.length())));
      }
    }
    return new Value(s);
  }

  private static boolean isAbs(String s) {
    return s.equalsIgnoreCase(ABS);
  }

  private static boolean isSin(String s) {
    return s.equalsIgnoreCase(SIN);
  }

  private static boolean isCos(String s) {
    return s.equalsIgnoreCase(COS);
  }

  private static boolean isPlusOrMinus(char c) {
    return c == PLUS || c == MINUS;
  }

  private static boolean isMultiplicationOrDivision(char c) {
    return c == MULTIPLICATION || c == DIVISION;
  }

  private static boolean isValueWithE(int position, String s) {
    return position != 0 && isE(s.charAt(position - 1));
  }

  private static boolean isE(char c) {
    return c == SMALL_E || c == BIG_E;
  }
}
