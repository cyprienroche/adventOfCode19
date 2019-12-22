package UniversalOrbitMap;

import java.util.Arrays;

public class UMO {

  int count;

  public UMO(String[] strings) {
    count = 0;
    Arrays.stream(strings).forEach(this::addOrbit);
  }

  private void addOrbit(String orbit) {
    count += 1;
  }

  public int getOrbitNumber() {
    return count;
  }
}
