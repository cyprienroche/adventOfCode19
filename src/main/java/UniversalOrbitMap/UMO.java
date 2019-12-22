package UniversalOrbitMap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

public class UMO {

  private Map<String, String> orbitMap;

  public UMO(String[] strings) {
    orbitMap = new HashMap<>();
    Arrays.stream(strings).forEach(this::addOrbit);
  }

  private void addOrbit(String orbit) {
    String[] split = orbit.split("\\)");
    orbitMap.put(split[0], split[1]);
  }

  private int countOrbit(String orbitCenter, String orbitingPlanet) {
    int oldCount = 0;
    Optional<Entry<String, String>> grandparent = orbitMap
        .entrySet()
        .stream()
        .filter(e -> e.getValue().equals(orbitCenter))
        .findFirst();
    if (grandparent.isPresent()) {
      oldCount = countOrbit(grandparent.get().getKey(), orbitCenter);
    }
    return 1 + oldCount;
  }

/*  private void addOrbit(String orbit) {
    String[] split = orbit.split("\\)");
    int orbitedCount = orbitMap.getOrDefault(split[0], 0);
    if (orbitMap.containsKey(split[1])) {
      System.out.println("Haha");
    }
    orbitMap.put(split[1], orbitedCount + 1);
  }*/

  public int getOrbitNumber() {
    return orbitMap.entrySet().stream().map(e -> countOrbit(e.getKey(), e.getValue())).mapToInt(x -> x).sum();
  }
}
