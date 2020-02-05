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
            } catch (Exception e){
                continue;
            }
        }
        throw new GaugeParserException(node, "Maybe Error");
    }
}
