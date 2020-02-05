package de.klosebrothers.specparser.gauge.parser;

import de.klosebrothers.specparser.gauge.datastructure.ContextSteps;
import org.commonmark.node.Node;

import static java.util.Collections.singletonList;

public class ContextStepsParser extends GaugeParser {
    @Override
    protected FromTo parse(Node node) {
        ContextSteps steps = new ContextSteps();
        FromTo fromTo = many(node, steps, new OneOf(stepParser, commentParser));
        return new FromTo(fromTo.from, singletonList(steps));
    }
}
