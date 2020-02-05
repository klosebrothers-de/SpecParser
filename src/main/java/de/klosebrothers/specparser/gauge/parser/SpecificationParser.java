package de.klosebrothers.specparser.gauge.parser;

import de.klosebrothers.specparser.gauge.datastructure.Specification;
import org.commonmark.node.Node;

import static java.util.Collections.singletonList;

public class SpecificationParser extends GaugeParser {

    @Override
    public FromTo parse(Node node) {
        FromTo fromTo;
        Specification specification = new Specification();
        fromTo = many(node.getFirstChild(), specification, commentParser);
        fromTo = headingParser.parseAdd(fromTo.from, specification);
        fromTo = many(fromTo.from, specification, commentParser);
        fromTo = maybe(fromTo.from, specification, tagsParser);
        fromTo = many(fromTo.from, specification, commentParser);
        fromTo = maybe(fromTo.from, specification, contextStepsParser);
        fromTo = many(fromTo.from, specification, commentParser);
        fromTo = scenarioParser.parseAdd(fromTo.from, specification);
        fromTo = many(fromTo.from, specification, new OneOf(commentParser, scenarioParser));
        fromTo = maybe(fromTo.from, specification, teardownStepsParser);
        return new FromTo(fromTo.from, singletonList(specification));
    }
}
