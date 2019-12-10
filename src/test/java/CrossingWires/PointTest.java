package CrossingWires;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class PointTest {

  private final Point p = new Point(0, 0);


  @Test
  public void canEquatePaths() {
    assertEquals(p,p);
    assertEquals(new Point(0,0), p);
    assertNotEquals(new Point(1,0), p);
    assertNotEquals(new Point(0,1), p);
    assertNotEquals(new Point(1,1), new Point(-1,-1));
  }

  @Test
  public void equalPathsHaveEqualHash() {
    assertEquals(p.hashCode(), p.hashCode());
    assertEquals(new Point(0,0).hashCode(), p.hashCode());
    assertEquals(new Point(0,0).hashCode(), new Point(0,0).hashCode());
    assertNotEquals(new Point(1,0).hashCode(), p.hashCode());
    assertNotEquals(new Point(0,1).hashCode(), p.hashCode());
  }

  @Test
  public void canGetManhattanDistance() {
    assertThat(p.manhattanDistanceFromOrigin(), is(0));
    assertThat(new Point(10,0).manhattanDistanceFromOrigin(), is(10));
    assertThat(new Point(0,10).manhattanDistanceFromOrigin(), is(10));
    assertThat(new Point(10,10).manhattanDistanceFromOrigin(), is(20));
  }
}