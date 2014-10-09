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
    Assert.assertTrue("abs(-1)", calculator.calculate("abs(-1)") == 1);
    Assert.assertTrue("abs(-1*234-324-32)", calculator.calculate("abs(-1*234-324-32)") == Math.abs(-1 * 234 - 324 - 32));
    Assert.assertTrue("3*aBs(-1*234-324-32)", calculator.calculate("3*aBs(-1*234-324-32)") == 3 * Math.abs(-1 * 234 - 324 - 32));
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
    Assert.assertTrue(calculator.calculate("(-(+(-(+(-(-5e5))))))") == 5e5);
    Assert.assertTrue(calculator.calculate("(-(+(-(+(-(-5e+5))))))") == 5e5);
    Assert.assertTrue(calculator.calculate("234e2") == 234e2);
    Assert.assertTrue(calculator.calculate("234e-2") == 234e-2);
  }

  @Test
  public void negativeUnarySignTest() {
    Assert.assertTrue(calculator.calculate("-(7)") == -7);
    Assert.assertTrue(calculator.calculate("-(7)*(-6)*(-1)") == -(7) * (-6) * (-1));
    Assert.assertTrue(calculator.calculate("1-(7)*(-6)*(-1)") == 1 - (7) * (-6) * (-1));
    Assert.assertTrue(calculator.calculate("(-7)^2") == 49);
    Assert.assertTrue(calculator.calculate("+(-7)^2") == 49);
    Assert.assertTrue(calculator.calculate("(12E-2)^2") == Math.pow(12E-2, 2));
    Assert.assertTrue(calculator.calculate("-5*(-8)") == 40);
    Assert.assertTrue(calculator.calculate("(-5)*(-(-8))") == -40);
    Assert.assertTrue(calculator.calculate("-(-abs(-4))") == 4);
    Assert.assertTrue(calculator.calculate("-abs(-4)") == -4);
  }

  @Test
  public void powerTest() {
    Assert.assertTrue("-45^(-1)", calculator.calculate("-45^(-1)") == Math.pow(-45, (-1)));
    Assert.assertTrue("-2^(2)", calculator.calculate("-2^(2)") == -Math.pow(2, (2)));
    Assert.assertTrue("(-2)^10", calculator.calculate("(-2)^10") == Math.pow(2, 10));

    Assert.assertTrue("2^(0.2)", calculator.calculate("2^(0.2)") == Math.pow(2, 0.2));
    Assert.assertTrue("(-2)^5", calculator.calculate("(-2)^5") == -Math.pow(2, 5));
    Assert.assertTrue("2^(-2)", calculator.calculate("2^(-2)") == Math.pow(2, -2));
    Assert.assertTrue("16^(0.5)", calculator.calculate("16^(0.5)") == 4);

    Assert.assertTrue("25^(0.5)", calculator.calculate("25^(0.5)") == 5);
    Assert.assertTrue("0.04^(0.5)", calculator.calculate("0.04^(0.5)") == 0.2);
    Assert.assertTrue("23^0", calculator.calculate("23^0") == 1);
    Assert.assertTrue("4+6^2+4", calculator.calculate("4+6^2+4") == 4 + 36 + 4);
    Assert.assertTrue("4^0+5^0-6^0", calculator.calculate("4^0+5^0-6^0") == 1);

    Assert.assertTrue("3^3^3^3", calculator.calculate("3^3^3^3") == Math.pow(3, Math.pow(3, 9)));
    Assert.assertTrue("-2^(2)", calculator.calculate("-2^(2)") == -Math.pow(2, (2)));
  }

  @Test
  public void complexTest() {
    Assert.assertTrue("34-53*34.4+4-cos(4.5)/4*44.3-(45)*(12.12)+(34)^(3)", calculator.calculate("34-53*34.4+4-cos(4.5)/4*44.3-(45)*(12.12)+(34)^(3)") == 34.0 - 53 * 34.4 + 4 - (Math.cos(4.5)) / 4 * 44.3 - (45) * (12.12) + Math.pow(34, 3));
    Assert.assertTrue("(abs(-(-23))-abs(32.3))*12+0-0-0-0-0-0-0*0", calculator.calculate("(abs(-(-23))-abs(32.3))*12+0-0-0-0-0-0-0*0") == (Math.abs(-(-23)) - Math.abs(32.3)) * 12.0 + 0 - 0 - 0 - 0 - 0 - 0 - 0);
    Assert.assertTrue("45345/3453+234234^2+2342/233", calculator.calculate("45345/3453+234234^2+2342/233") == 45345.0 / 3453 + Math.pow(234234, 2) + 2342.0 / 233);
    Assert.assertTrue("cos(sin(5))+abs(cos(5))", calculator.calculate("cos(sin(5))+abs(cos(5))") == Math.cos(Math.sin(5)) + Math.abs(Math.cos(5)));
    Assert.assertTrue("(abs(-32.3))+(abs(-32.3))^(abs(-32.3))-(abs(-32.3))*(abs(-32.3))+(abs(-32.3))/(abs(-32.3))", calculator.calculate("(abs(-32.3))+(abs(-32.3))^(abs(-32.3))-(abs(-32.3))*(abs(-32.3))+(abs(-32.3))/(abs(-32.3))") == (32.3) + Math.pow((32.3), (32.3)) - (32.3) * (32.3) + (32.3) / (32.3));
    Assert.assertTrue("(-(+(-(+(-(5))))))", calculator.calculate("(-(+(-(+(-(5))))))") == -5);

  }


  @Test
  public void currentTest() {
    Assert.assertTrue("abs(-1*234-324-32)", calculator.calculate("abs(-1*234-324-32)") == Math.abs(-1 * 234 - 324 - 32));
  }

  @Test
  public void unaryMultiTest() {
    Assert.assertTrue("--4", calculator.calculate("--4") == 4);
    Assert.assertTrue(calculator.calculate("--+++-++-++----4") == 4);
    Assert.assertTrue(calculator.calculate("--+++-++-++----4-3") == 1);
    Assert.assertTrue(calculator.calculate("--2^2") == 4);
    Assert.assertTrue(calculator.calculate("---3---4") == (-3)-(4));
    Assert.assertTrue(calculator.calculate("2*-3") == -6);
  }

  @Test
  public void power2Test() {
    Assert.assertTrue(calculator.calculate("0^3") == 0);
    Assert.assertTrue(calculator.calculate("0^4.5") == 0);
    Assert.assertTrue(calculator.calculate("0^0") == 1);
    Assert.assertTrue(calculator.calculate("0^0") == 1);
    Assert.assertTrue(calculator.calculate("-3^0") == -1);
    Assert.assertTrue(calculator.calculate("0^5") == 0);
    Assert.assertTrue(calculator.calculate("2^-2") == 0.25);


  }

}
