package de.klosebrothers.specparser.gauge.parser;

import de.klosebrothers.specparser.gauge.datastructure.TearDownSteps;
import org.commonmark.node.Node;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

public class TeardownStepsParser extends GaugeParser {
    @Override
    protected FromTo parse(Node node) {
        if (node == null) {
            return new FromTo(null, emptyList());
        }
        TearDownSteps steps = new TearDownSteps();
        many(node.getNext(), steps, new MaybeOneOf(stepParser, commentParser));
        return new FromTo(node.getNext(), singletonList(steps));
    }
}
