package UniversalOrbitMap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class UMO {

  Map<String, Integer> orbitMap;
  int count;

  public UMO(String[] strings) {
    orbitMap = new HashMap<>();
    count = 0;
    Arrays.stream(strings).forEach(this::addOrbit);
  }

  private void addOrbit(String orbit) {
    String[] split = orbit.split("\\)");
    int orbitedCount = orbitMap.getOrDefault(split[0], 0);
    orbitMap.put(split[1], orbitedCount + 1);
    count += 1;
  }

  public int getOrbitNumber() {
    return orbitMap.values().stream().mapToInt(x -> x).sum();
  }
}
