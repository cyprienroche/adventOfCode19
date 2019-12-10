package CrossingWires;

import java.util.Arrays;

public enum Direction {
  UP(0,1),
  LEFT(-1,0),
  DOWN(0,-1),
  RIGHT(1,0);

  private final int dx;
  private final int dy;

  Direction(int dx, int dy) {
    this.dx = dx;
    this.dy = dy;
  }

  public int getX() {
    return dx;
  }

  public int getY() {
    return dy;
  }

  private char directionCode() {
    return name().charAt(0);
  }
  public static Direction getDirection(char d) {
    return Arrays.stream(values()).filter(direction -> direction.directionCode() == d).findAny().get();
  }
}
