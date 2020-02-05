package de.klosebrothers.specparser.gauge.parser;

import org.commonmark.node.Node;

public class OneOf extends GaugeParser{
    private GaugeParser[] parsers;

    public OneOf(GaugeParser ... parsers) {
        this.parsers = parsers;
    }

    @Override
    protected FromTo parse(Node node) {
        for (GaugeParser parser : parsers) {
            try {
                return parser.parse(node);
            } catch (GaugeParserException e){
                throw e;
            } catch (WrongGaugeParserException e){
                continue;
            }
        }
        throw new RuntimeException("This shouldn't happen, please contact author with failing Gauge Specification File");
    }
}
