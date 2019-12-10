package CrossingWires;

import static java.lang.Math.abs;
import static java.lang.String.format;
import static java.util.Objects.hash;

public class Point {
  private int x;
  private int y;

  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public boolean equals(Object o) {
    return o instanceof Point && x == ((Point) o).x && y == ((Point) o).y;
  }

  @Override
  public int hashCode() {
    return hash(x, y);
  }

  @Override
  public String toString() {
    return format("<%d,%d>", x, y);
  }

  public int manhattanDistanceFromOrigin() {
    return abs(x) + abs(y);
  }
}
