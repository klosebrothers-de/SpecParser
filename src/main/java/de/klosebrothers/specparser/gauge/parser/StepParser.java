package de.klosebrothers.specparser.gauge.parser;

import de.klosebrothers.specparser.gauge.datastructure.Step;
import org.commonmark.node.*;

public class StepParser extends GaugeParser {
    @Override
    public FromTo parse(Node node) {
        if (!(node instanceof BulletList)) {
            throw new WrongParserException(node, this);
        }



        return new FromTo(node.getNext(), step);
    }
}
