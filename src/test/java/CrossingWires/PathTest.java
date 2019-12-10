package CrossingWires;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class PathTest {

  private final Point point = new Point(0, 0);
  private final Path p = new Path(point, 0);


  @Test
  public void canEquatePoints() {
    assertEquals(p,p);
    assertEquals(new Path(point, 0), p);
    assertNotEquals(new Path(point, 1), p);
    assertNotEquals(new Path(new Point(1,1), 0), p);
    assertNotEquals(new Path(new Point(-1,-1), 1), new Path(new Point(1,1), -1));
  }

  @Test
  public void equalPointsHaveEqualHash() {
    assertEquals(p.hashCode(), p.hashCode());
    assertEquals(new Path(point, 0).hashCode(), p.hashCode());
    assertEquals(new Path(point, 0).hashCode(), new Path(point, 0).hashCode());
    assertNotEquals(new Path(point, 1).hashCode(), p.hashCode());
    assertNotEquals(new Path(new Point(1,1), 0).hashCode(), p.hashCode());
  }

  @Test
  public void canAccessFields() {
    assertThat(p.steps(), is(0));
    assertThat(new Path(new Point(1,1),0).point(), is(new Point(1,1)));
    assertThat(new Path(new Point(1,1),10).steps(), is(10));
  }

}