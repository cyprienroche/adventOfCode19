package CrossingWires;

import java.util.Objects;

public class Path {

  //not used anymore, use Map instead.. !
  private final Point point;
  private final int steps;

  public Path(Point point, int steps) {
    this.point = point;
    this.steps = steps;
  }

  public Point point() {
    return point;
  }

  public int steps() {
    return steps;
  }

  @Override
  public boolean equals(Object o) {
    return o instanceof Path &&
        steps == ((Path) o).steps && point.equals(((Path) o).point);
  }

  @Override
  public int hashCode() {
    return Objects.hash(point, steps);
  }

  @Override
  public String toString() {
    return String.format("<%s,%d>", point.toString(), steps);
  }
}
