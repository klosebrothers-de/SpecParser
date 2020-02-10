package de.klosebrothers.specparser.gauge.parser;

import de.klosebrothers.specparser.gauge.datastructure.Steps;
import org.commonmark.node.Node;

import static java.util.Collections.singletonList;

public class StepsParser extends GaugeParser {
    @Override
    protected FromTo parse(Node node) {
        if (node == null) {
            throw new GaugeParserException(this, null, "Can't parse nullpointer, reached end of file?");
        }
        Steps steps = new Steps();
        FromTo fromTo = many(node, steps, new MaybeOneOf(stepParser, commentParser));
        return new FromTo(fromTo.from, singletonList(steps));
    }
}
