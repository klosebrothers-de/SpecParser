package de.klosebrothers.specparser.gauge.parser;

import org.commonmark.node.Node;

public class ContextStepsParser extends GaugeParser {
    @Override
    protected FromTo parse(Node node) {
        throw new WrongParserException(node, this);
    }
}
