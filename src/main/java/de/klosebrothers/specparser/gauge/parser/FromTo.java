package de.klosebrothers.specparser.gauge.parser;

import de.klosebrothers.specparser.gauge.datastructure.Component;
import org.commonmark.node.Node;

import java.util.List;

public class FromTo {
    public final Node from;
    public final List<Component> to;

    public FromTo(Node from, List<Component> to) {
        this.from = from;
        this.to = to;
    }
}
