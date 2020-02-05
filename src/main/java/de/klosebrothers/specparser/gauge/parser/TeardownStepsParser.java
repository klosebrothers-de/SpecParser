package de.klosebrothers.specparser.gauge.parser;

import de.klosebrothers.specparser.gauge.datastructure.ContextSteps;
import de.klosebrothers.specparser.gauge.datastructure.TearDownSteps;
import org.commonmark.node.Node;

import static java.util.Collections.singletonList;

public class TeardownStepsParser extends GaugeParser {
    @Override
    protected FromTo parse(Node node) {
        TearDownSteps steps = new TearDownSteps();
        many(node.getNext(), steps, new OneOf(stepParser, commentParser));
        return new FromTo(node.getNext(), singletonList(steps));
    }
}
