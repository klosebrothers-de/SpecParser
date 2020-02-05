package de.klosebrothers.specparser.gauge.parser;

import de.klosebrothers.specparser.gauge.datastructure.Scenario;
import org.commonmark.node.Node;

public class ScenarioParser extends GaugeParser {
    @Override
    protected FromTo parse(Node node) {
        Scenario scenario = new Scenario();
        FromTo fromTo;
        fromTo = scenarioHeadingParser.parseAdd(node, scenario);
        fromTo = many(fromTo.from, scenario, commentParser);
        fromTo = maybe(fromTo.from, scenario, tagsParser);
        fromTo = many(fromTo.from, scenario, commentParser);
        fromTo = stepsParser.parseAdd(fromTo.from, scenario);
        fromTo = many(fromTo.from, scenario, commentParser);
        return fromTo;
    }
}
