package de.klosebrothers.specparser.gauge.parser;

import org.commonmark.node.Node;

public class WrongParserException extends RuntimeException {
    public WrongParserException(Node node, GaugeParser parser) {
        super("At: " + node.toString() + "tried with Parser " + parser.toString() + ", but failed");
    }
}
