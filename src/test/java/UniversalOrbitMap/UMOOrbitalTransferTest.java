package UniversalOrbitMap;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UMOOrbitalTransferTest {

  @Test
  public void simpleCase1() {
    UMO umo = new UMO(new String[]{
        "COM)YOU",
        "COM)SAN",
    });
    assertEquals(0, umo.orbitsYOUToSAN());
  }

  @Test
  public void simpleCase2() {
    UMO umo = new UMO(new String[]{
        "COM)B",
        "B)YOU",
        "B)SAN",
    });
    assertEquals(0, umo.orbitsYOUToSAN());
  }

  @Test
  public void simpleCase3() {
    UMO umo = new UMO(new String[]{
        "COM)B",
        "COM)YOU",
        "B)SAN",
    });
    assertEquals(1, umo.orbitsYOUToSAN());
  }

  @Test
  public void simpleCase4() {
    UMO umo = new UMO(new String[]{
        "COM)B",
        "B)YOU",
        "COM)SAN",
    });
    assertEquals(1, umo.orbitsYOUToSAN());
  }
}
