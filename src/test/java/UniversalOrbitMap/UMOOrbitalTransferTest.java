package UniversalOrbitMap;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UMOOrbitalTransferTest {

  @Test
  public void sameOrbitReturnsZero() {
    UMO umo = new UMO(new String[]{
        "COM)YOU",
        "COM)SAN",
    });
    assertEquals(0, umo.orbitsYOUToSAN());
  }

  @Test
  public void sameOrbitReturnsZero2() {
    UMO umo = new UMO(new String[]{
        "COM)B",
        "B)YOU",
        "B)SAN",
    });
    assertEquals(0, umo.orbitsYOUToSAN());
  }

  @Test
  public void offByOneOrbitReturnsOne() {
    UMO umo = new UMO(new String[]{
        "COM)B",
        "COM)YOU",
        "B)SAN",
    });
    assertEquals(1, umo.orbitsYOUToSAN());
  }

  @Test
  public void offByOneOrbitReturnsOne2() {
    UMO umo = new UMO(new String[]{
        "COM)B",
        "B)YOU",
        "COM)SAN",
    });
    assertEquals(1, umo.orbitsYOUToSAN());
  }

  @Test
  public void planetInBetweenOnSameOrbitLevel() {
    UMO umo = new UMO(new String[]{
        "COM)B",
        "B)C",
        "B)D",
        "C)YOU",
        "D)SAN",
    });
    assertEquals(2, umo.orbitsYOUToSAN());
  }

  @Test
  public void complexExample() {
    UMO umo = new UMO(new String[]{
        "COM)B",
        "B)C",
        "C)D",
        "D)E",
        "E)F",
        "B)G",
        "G)H",
        "D)I",
        "E)J",
        "J)K",
        "K)L",
        "K)YOU",
        "I)SAN",
    });
    assertEquals(4, umo.orbitsYOUToSAN());
  }
}
