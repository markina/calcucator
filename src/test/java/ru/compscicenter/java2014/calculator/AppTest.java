package ru.compscicenter.java2014.calculator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AppTest {

  private boolean value;

  @Before
  public void setup() {
    value = true;
  }

  @Test
  public void passing() {
    Assert.assertTrue(value);
  }

  @Test
  public void failing() {
    Assert.assertFalse(value);
  }
}
