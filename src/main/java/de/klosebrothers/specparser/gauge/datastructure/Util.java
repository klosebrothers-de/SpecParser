package de.klosebrothers.specparser.gauge.datastructure;

import java.util.List;
import java.util.stream.Collectors;

public class Util {

    public static <A> A findFirst(List<Component> components, Class<A> componentClass) {
        return (A) components.stream().filter(c -> componentClass.isInstance(c)).findFirst().orElseThrow();
    }

    public static <A> List<A> findAll(List<Component> components, Class<A> componentClass) {
        return components.stream().filter(c -> componentClass.isInstance(c)).map(c -> (A) c).collect(Collectors.toList());
    }
}
