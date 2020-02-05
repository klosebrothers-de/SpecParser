package de.klosebrothers.specparser.gauge.parser;

import org.commonmark.node.Node;

public class GaugeParserException extends RuntimeException {
    public GaugeParserException(Node fromTo, String errorMsg) {
        super("At "+ fromTo.toString() +":\n"+ errorMsg);
    }
}
