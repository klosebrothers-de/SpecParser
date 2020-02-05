package de.klosebrothers.specparser.gauge.datastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Component {
    protected List<Component> branches = new ArrayList<>();
    public void addAllComponents(List<Component> component){
        branches.addAll(component);
    }
    public abstract String toMD();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Component component = (Component) o;
        return branches.equals(component.branches);
    }

    @Override
    public int hashCode() {
        return Objects.hash(branches);
    }
}
