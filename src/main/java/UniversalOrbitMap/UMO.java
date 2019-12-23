package UniversalOrbitMap;

import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

public class UMO {

  private Set<Entry<String, String>> orbitSet;
  private String src;
  private String dst;

  public UMO(String[] strings) {
    orbitSet = new HashSet<>();
    Arrays.stream(strings).forEach(this::addOrbit);
  }

  private void addOrbit(String orbit) {
    String[] split = orbit.split("\\)");
    if (split[1].equals("YOU")) {
      src = split[0];
    } else if (split[1].equals("SAN")){
      dst = split[0];
    }
    orbitSet.add(new SimpleEntry<>(split[0], split[1]));
  }

  private int countOrbit(String orbitCenter) {
    Optional<Entry<String, String>> grandparent = orbitSet
        .stream()
        .filter(e -> e.getValue().equals(orbitCenter))
        .findFirst();
    return 1 + grandparent.map(e -> countOrbit(e.getKey())).orElse(0);
  }

  public int getOrbitNumber() {
    return orbitSet.stream().map(e -> countOrbit(e.getKey())).mapToInt(x -> x).sum();
  }

  public int orbitsYOUToSAN() {
    return Math.abs(countOrbit(dst) - countOrbit(src));
  }
}
