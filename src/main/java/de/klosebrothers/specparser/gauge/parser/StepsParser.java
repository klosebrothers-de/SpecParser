package de.klosebrothers.specparser.gauge.parser;

import de.klosebrothers.specparser.gauge.datastructure.Steps;
import org.commonmark.node.Node;

public class StepsParser extends GaugeParser {
    @Override
    protected FromTo parse(Node node) {
        Steps steps = new Steps();
        many(node, steps, new OneOf(stepParser, commentParser));
        return new FromTo(node.getNext(), steps);
    }
}
