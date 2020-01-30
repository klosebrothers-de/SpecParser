package de.klosebrothers.specparser.gauge;

import java.util.ArrayList;
import java.util.List;

public abstract class Component {
    protected List<Component> branches = new ArrayList<>();
}
