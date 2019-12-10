package PasswordPermutation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PasswordPermutationTest {

  @Test
  public void isValidPassword() {
    assertTrue(PasswordPermutation.isValid(111111));
    assertFalse(PasswordPermutation.isValid(223450));
    assertFalse(PasswordPermutation.isValid(123789));
  }

  @Test
  public void isValidPasswordWithExactlyTwoDigits() {
    assertTrue(PasswordPermutation.isValidWithExactlyTwoDigits(112233));
    assertFalse(PasswordPermutation.isValidWithExactlyTwoDigits(123444));
    assertTrue(PasswordPermutation.isValidWithExactlyTwoDigits(111122));
    assertTrue(PasswordPermutation.isValidWithExactlyTwoDigits(123455));
    assertTrue(PasswordPermutation.isValidWithExactlyTwoDigits(123345));
    assertTrue(PasswordPermutation.isValidWithExactlyTwoDigits(112345));
  }

}