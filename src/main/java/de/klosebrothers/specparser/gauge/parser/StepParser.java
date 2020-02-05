package de.klosebrothers.specparser.gauge.parser;

import de.klosebrothers.specparser.gauge.datastructure.Component;
import de.klosebrothers.specparser.gauge.datastructure.Step;
import org.commonmark.node.*;

import java.util.ArrayList;
import java.util.List;

public class StepParser extends GaugeParser {
    @Override
    public FromTo parse(Node node) {
        if (!(node instanceof BulletList)) {
            throw new WrongGaugeParserException(node, this);
        }

        List<Component> steps = new ArrayList<>();

        Node listItem = node.getFirstChild();
        while (listItem != null) {
            String stepText = ((Text) listItem.getFirstChild().getFirstChild()).getLiteral();
            steps.add(new Step(stepText));
            listItem = listItem.getNext();
        }

        return new FromTo(node.getNext(), steps);
    }
}
