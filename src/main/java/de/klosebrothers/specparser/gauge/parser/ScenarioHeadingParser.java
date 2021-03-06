package de.klosebrothers.specparser.gauge.parser;

import de.klosebrothers.specparser.gauge.datastructure.ScenarioHeading;
import org.commonmark.node.Node;
import org.commonmark.node.Text;

import static java.util.Collections.singletonList;

public class ScenarioHeadingParser extends GaugeParser {

    @Override
    protected FromTo parse(Node node) {
        if (!(node instanceof org.commonmark.node.Heading))
            throw new WrongGaugeParserException(node, this);

        String literal = ((Text) node.getFirstChild()).getLiteral();
        ScenarioHeading heading = new ScenarioHeading(literal);
        return new FromTo(node.getNext(), singletonList(heading));
    }
}
