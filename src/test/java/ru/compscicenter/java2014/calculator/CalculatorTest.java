package ru.compscicenter.java2014.calculator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Markina Margarita on 05.10.14.
 */

public class CalculatorTest {

  Calculator calculator;

  @Before
  public void setup() {
    calculator = new CalculatorImpl();
  }

  @Test
  public void simpleTest() {
    Assert.assertTrue(calculator.calculate("5") == 5);
    Assert.assertTrue(calculator.calculate("5+1") == (5 + 1));
    Assert.assertTrue(calculator.calculate("5*20") == (5 * 20));
    Assert.assertTrue(calculator.calculate("5*20+2") == (5 * 20 + 2));
    Assert.assertTrue(calculator.calculate("0") == (0));
    Assert.assertTrue(calculator.calculate("-1*234-324-32") == (-1 * 234 - 324 - 32));

  }

  @Test
  public void degreeTest() {
    Assert.assertTrue(calculator.calculate("5*20^2") == 5 * Math.pow(20, 2));
    Assert.assertTrue(calculator.calculate("2^(4^4)") == (Math.pow(2, 256)));
    Assert.assertTrue(calculator.calculate("2^4^4") == (Math.pow(2, 256)));
    Assert.assertTrue(calculator.calculate("4+(2^2)^(3^2)*6") == (4 + Math.pow(4, 9) * 6));
    Assert.assertTrue(calculator.calculate("4+(2^2)-3^2*6") == 4 + 4 - 9 * 6);
  }

  @Test
  public void unaryOperationTest() {
    Assert.assertTrue(calculator.calculate("-1") == -1);
    Assert.assertTrue(calculator.calculate("-1*5") == -5);
    Assert.assertTrue(calculator.calculate("+1*(-5)") == -5);

  }

  @Test
  public void sinCosTest() {
    Assert.assertTrue(calculator.calculate("cos(4)") == Math.cos(4));
    Assert.assertTrue(calculator.calculate("cos(-4)") == Math.cos(-4));
    Assert.assertTrue(calculator.calculate("-cos(35)*6") == -Math.cos(35) * 6);
    Assert.assertTrue(calculator.calculate("-sin(44)*6") == -Math.sin(44) * 6);
    Assert.assertTrue(calculator.calculate("sIn(44)") == Math.sin(44));
    Assert.assertTrue(calculator.calculate("COs(44)") == Math.cos(44));
  }

  @Test
  public void absTest() {
    Assert.assertTrue(calculator.calculate("abs(-1)") == 1);
    Assert.assertTrue(calculator.calculate("abs(-1*234-324-32)") == Math.abs(-1 * 234 - 324 - 32));
    Assert.assertTrue(calculator.calculate("3*aBs(-1*234-324-32)") == 3 * Math.abs(-1 * 234 - 324 - 32));
  }

  @Test
  public void strongTest() {
    Assert.assertTrue(calculator.calculate("4^3-(cos(5)+sin(5)*7^2+45-(-(abs(-5)))*2)") == Math.pow(4, 3) - (Math.cos(5) + Math.sin(5) * Math.pow(7, 2) + 45 - (-(Math.abs(-5))) * 2));
    Assert.assertTrue(calculator.calculate("(cos(5)+sin(5))/(-12)") == (Math.cos(5) + Math.sin(5)) / (-12));
  }

  @Test
  public void eTest() {
    Assert.assertTrue(calculator.calculate("-Abs(1E+1^2 + 20/ 2)") == -110);
    Assert.assertTrue(calculator.calculate("1E+1") == 1E+1);
    Assert.assertTrue(calculator.calculate("-1E+22-213+234*45") == -1E+22 - 213 + 234 * 45);
    Assert.assertTrue(calculator.calculate("-1E+22-21E-12+45E-12") == -1E+22 - 21E-12 + 45E-12);
  }

  @Test
  public void negativeUnarySignTest() {
    Assert.assertTrue(calculator.calculate("-(7)") == -7);
    Assert.assertTrue(calculator.calculate("-(7)*(-6)*(-1)") == -(7) * (-6) * (-1));
    Assert.assertTrue(calculator.calculate("1-(7)*(-6)*(-1)") == 1 - (7) * (-6) * (-1));
    Assert.assertTrue(calculator.calculate("(-7)^2") == 49);
    Assert.assertTrue(calculator.calculate("+(-7)^2") == 49);
    Assert.assertTrue(calculator.calculate("(12E-2)^2") == Math.pow(12E-2, 2));
  }

  @Test
  public void powerTest() {
    Assert.assertTrue(calculator.calculate("-45^(-1)") == Math.pow(-45, (-1)));
    Assert.assertTrue(calculator.calculate("-2^(2)") == -Math.pow(2, (2)));
    Assert.assertTrue(calculator.calculate("(-2)^10") == Math.pow(2, 10));

    Assert.assertTrue(calculator.calculate("2^(0.2)") == Math.pow(2, 0.2));
    Assert.assertTrue(calculator.calculate("(-2)^5") == -Math.pow(2, 5));
    Assert.assertTrue(calculator.calculate("2^(-2)") == 1.0 / 4);
    Assert.assertTrue(calculator.calculate("16^(0.5)") == 4);

    Assert.assertTrue(calculator.calculate("25^(0.5)") == 5);
    Assert.assertTrue(calculator.calculate("0.04^(0.5)") == 0.2);
    Assert.assertTrue(calculator.calculate("23^0") == 1);
  }

  @Test
  public void currentTest() {

  }


}
