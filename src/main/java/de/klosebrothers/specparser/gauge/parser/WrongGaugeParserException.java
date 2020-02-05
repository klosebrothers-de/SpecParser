package de.klosebrothers.specparser.gauge.parser;

import org.commonmark.node.Node;

public class WrongGaugeParserException extends RuntimeException {
    public WrongGaugeParserException(Node node, GaugeParser parser) {
        this(node, parser, null);
    }

    public WrongGaugeParserException(Node node, GaugeParser parser, String msg) {
        super("At: " + (node == null ? "null" : node) + "tried with Parser " + parser +
                (msg == null ? "" : ", but failed with message \"" + msg + "\""));
    }
}
