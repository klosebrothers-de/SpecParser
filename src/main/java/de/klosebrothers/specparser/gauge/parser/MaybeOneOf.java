package de.klosebrothers.specparser.gauge.parser;

import org.commonmark.node.Node;

import java.util.Arrays;

public class MaybeOneOf extends GaugeParser {
    private GaugeParser[] parsers;

    public MaybeOneOf(GaugeParser... parsers) {
        this.parsers = parsers;
    }

    @Override
    protected FromTo parse(Node node) {
        for (GaugeParser parser : parsers) {
            try {
                return parser.parse(node);
            } catch (GaugeParserException e) {
                throw e;
            } catch (WrongGaugeParserException e) {
                continue;
            }
        }
        throw new WrongGaugeParserException(node, this, "Should be one of " + Arrays.toString(parsers) + ", but wasn't");
    }
}
