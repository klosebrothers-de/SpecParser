package de.klosebrothers.specparser.gauge;

import de.klosebrothers.specparser.gauge.datastructure.Component;
import de.klosebrothers.specparser.gauge.datastructure.Specification;
import de.klosebrothers.specparser.gauge.parser.GaugeParser;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;

import java.util.List;

import static de.klosebrothers.specparser.gauge.datastructure.Util.inorder;

public class SpecBuilder {
    public static String fromSpecification(Specification specification) {
        List<Component> inorder = inorder(specification);
        return inorder.stream().map(Component::toMD).reduce(String::concat).orElse("");
    }

    public static Specification toSpecification(String gauge) {
        Node rootNode = Parser.builder().build().parse(gauge);
        return (Specification) GaugeParser.specificationParser.parse(rootNode).to.get(0);
    }
}
