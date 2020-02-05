package de.klosebrothers.specparser.gauge;

import de.klosebrothers.specparser.gauge.datastructure.Specification;
import de.klosebrothers.specparser.gauge.parser.GaugeParser;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;

public class SpecBuilder {
    public static String fromSpecification(Specification specification) {

        return null;
    }

    public static Specification toSpecification(String gauge) {
        Node rootNode = Parser.builder().build().parse(gauge);
        return (Specification) GaugeParser.specificationParser.parse(rootNode).to.get(0);
    }
}
