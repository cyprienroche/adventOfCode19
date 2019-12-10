package CrossingWires;

import static CrossingWires.Direction.*;
import static java.lang.Integer.parseInt;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Wire {

  private final String[] path;
  private Map<Point, Integer> points = new HashMap<>();
  private int x = 0;
  private int y = 0;
  private int totalSteps = 0;

  public Wire(String[] path) {
    this.path = path;
    generateGrid();
  }

  private void generateGrid() {
    for (String cmd : path) {
      Direction direction = getDirection(cmd.charAt(0));
      int steps = parseInt(cmd.substring(1));
      addPoint(direction, steps);
    }
  }

  private void addPoint(Direction direction, int steps) {
    while (steps-- > 0) {
      x += direction.getX();
      y += direction.getY();
      points.putIfAbsent(new Point(x, y), ++totalSteps);
    }
  }

  public Set<Point> getPoints() {
    return new HashSet<>(Set.copyOf(points.keySet()));
  }

  public Integer getSteps(Point p) {
    return points.get(p);
  }

}
