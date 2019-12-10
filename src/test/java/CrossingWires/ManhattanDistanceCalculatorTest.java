package CrossingWires;

import static CrossingWires.ManhattanDistanceCalculator.shortestManhattanDistanceToOrigin;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ManhattanDistanceCalculatorTest {

  @Test
  public void parallelWiresDoNotCross() {
    Wire wire1 = new Wire(new String[]{"R5"});
    Wire wire2 = new Wire(new String[]{"L5"});
    assertThat(shortestManhattanDistanceToOrigin(wire1, wire2), is(-1));
  }

  @Test
  public void example1() {
    Wire wire1 = new Wire("R8,U5,L5,D3".split(","));
    Wire wire2 = new Wire("U7,R6,D4,L4".split(","));
    assertThat(shortestManhattanDistanceToOrigin(wire1,wire2), is(6));
  }

  @Test
  public void example2() {
    Wire wire1 = new Wire("R75,D30,R83,U83,L12,D49,R71,U7,L72".split(","));
    Wire wire2 = new Wire("U62,R66,U55,R34,D71,R55,D58,R83".split(","));
    assertThat(shortestManhattanDistanceToOrigin(wire1,wire2), is(159));
  }

  @Test
  public void example3() {
    Wire wire1 = new Wire("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51".split(","));
    Wire wire2 = new Wire("U98,R91,D20,R16,D67,R40,U7,R15,U6,R7".split(","));
    assertThat(shortestManhattanDistanceToOrigin(wire1,wire2), is(135));
  }
}
