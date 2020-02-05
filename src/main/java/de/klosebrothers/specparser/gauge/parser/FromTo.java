package de.klosebrothers.specparser.gauge.parser;

import de.klosebrothers.specparser.gauge.datastructure.Component;
import org.commonmark.node.Node;

public class FromTo {
    public final Node from;
    public final Component to;

    public FromTo(Node from, Component to) {
        this.from = from;
        this.to = to;
    }
}
