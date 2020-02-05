package de.klosebrothers.specparser.gauge.datastructure;

import java.util.ArrayList;
import java.util.List;

public abstract class Component {
    protected List<Component> branches = new ArrayList<>();
    public void addAllComponents(List<Component> component){
        branches.addAll(component);
    }
    public abstract String toMD();
}
