package FuelCalculator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import FuelCalculator.FuelCalculator;
import org.junit.Test;

public class FuelCalculatorTest {

  @Test
  public void findsTheCorrectFuelRequiredForAModule() {
    assertThat(FuelCalculator.getFuelRequirement(13), is(2));
    assertThat(FuelCalculator.getFuelRequirement(14), is(2));
    assertThat(FuelCalculator.getFuelRequirement(1969), is(654));
    assertThat(FuelCalculator.getFuelRequirement(100756), is(33583));
  }
}