package de.klosebrothers.specparser.gauge.parser;

import org.commonmark.node.Node;

public class WrongGaugeParserException extends RuntimeException {
    public WrongGaugeParserException(Node node, GaugeParser parser) {
        super("At: " + node.toString() + "tried with Parser " + parser.toString() + ", but failed");
    }
}
