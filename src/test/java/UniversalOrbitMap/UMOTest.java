package UniversalOrbitMap;

import static org.junit.Assert.*;

import org.junit.Test;

public class UMOTest {

  @Test
  public void basicOrbit() {
    UMO umo = new UMO(new String[]{"AAA)BBB"});
    assertEquals(1, umo.getOrbitNumber());
  }

  @Test
  public void example1() {
    UMO umo = new UMO(new String[]{
        "COM)B",
        "B)C",
    });
    assertEquals(3, umo.getOrbitNumber());
  }

  @Test
  public void example2() {
    UMO umo = new UMO(new String[]{
        "COM)B",
        "COM)C",
    });
    assertEquals(2, umo.getOrbitNumber());
  }


  @Test
  public void exampleComplex() {
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
    });
    assertEquals(42, umo.getOrbitNumber());
  }

  @Test
  public void exampleComplexChangingOrder() {
    UMO umo = new UMO(new String[]{
        "COM)B",
        "J)K",
        "K)L",
        "B)C",
        "C)D",
        "D)E",
        "E)F",
        "G)H",
        "D)I",
        "B)G",
        "E)J",
    });
    assertEquals(42, umo.getOrbitNumber());
  }
}