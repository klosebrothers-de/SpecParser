package de.klosebrothers.specparser.gauge.datastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Util {

    public static <A extends Component> A addIfNotPresent(List<Component> components, Class<A> componentClass) {
        Optional<A> maybeSteps = findFirst(components, componentClass);
        if (maybeSteps.isPresent()) {
            return maybeSteps.get();
        }

        A newInstance = null;
        try {
            newInstance = componentClass.newInstance();
            components.add(newInstance);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return newInstance;
    }


    public static <A extends Component> Optional<A> findFirst(List<Component> components, Class<A> componentClass) {
        return (Optional<A>) components.stream()
                .filter(c -> componentClass.isInstance(c))
                .findFirst();
    }

    public static <A> List<A> findAll(List<Component> components, Class<A> componentClass) {
        return components.stream().filter(c -> componentClass.isInstance(c)).map(c -> (A) c).collect(Collectors.toList());
    }

    public static List<Component> inorder(Component component) {
        return inorder(component, new ArrayList<>());
    }

    public static List<Component> inorder(Component component, List<Component> result) {
        result.add(component);
        for (Component branch : component.branches) {
            inorder(branch, result);
        }
        return result;
    }
}
