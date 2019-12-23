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
    return orbitalDistance(src, dst);
  }

  private int orbitalDistance(String src, String dst) {
    if (src.equals(dst)) {
      return 0;
    }
    Optional<Entry<String, String>> e1 = findPlanetOrbiting(src);
    Optional<Entry<String, String>> e2 = findPlanetOrbiting(dst);
    if (e1.isPresent() && e2.isPresent()) {
      return 2 + orbitalDistance(e1.get().getKey(), e2.get().getKey());
    }
    if (e1.isPresent()) {
      return 1 + orbitalDistance(e1.get().getKey(), dst);
    }
    if (e2.isPresent()) {
      return 1 + orbitalDistance(src, e2.get().getKey());
    }
    return 0;
  }

  private Optional<Entry<String, String>> findPlanetOrbiting(String src) {
    return orbitSet
        .stream()
        .filter(e -> e.getValue().equals(src))
        .findFirst();
  }
    /*
    String newSrc = getPlanetFromOptional(src);
    String newDst = getPlanetFromOptional(dst);

    if (newSrc.equals(src) && newDst.equals(dst)) {
      return 2 + orbitalDistance(newSrc, newDst);
    }
    if (newSrc.equals(src)) {
      return 1 + orbitalDistance(newSrc, newDst);
    }
    if (newDst.equals(dst)) {
      return 1 + orbitalDistance(newSrc, newDst);
    }
    return 0;
  }

  private String getPlanetFromOptional(String src) {
    return findPlanetOrbiting(src).map(Entry::getKey).orElse(src);
  }
*/
}
