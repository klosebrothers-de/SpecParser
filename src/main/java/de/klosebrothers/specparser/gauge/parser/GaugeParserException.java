package de.klosebrothers.specparser.gauge.parser;

import org.commonmark.node.Node;

public class GaugeParserException extends RuntimeException {
    public GaugeParserException(GaugeParser parser, Node node, String errorMsg) {
        super("Tried to parse " + (node == null ? "null" : node) + " with parser \"" +
                parser + "\" but failed with error message: \"" + errorMsg+"\"");
    }
}
