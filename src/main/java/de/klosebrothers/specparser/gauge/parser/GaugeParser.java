package de.klosebrothers.specparser.gauge.parser;

import de.klosebrothers.specparser.gauge.datastructure.Component;
import org.commonmark.node.Node;

import static java.util.Collections.singletonList;

public abstract class GaugeParser {
    protected abstract FromTo parse(Node node);

    public FromTo parseAdd(Node node, Component parent) {
        FromTo parse = parse(node);
        parent.addAllComponents(parse.to);
        return parse;
    }

    public static FromTo maybe(Node node, Component parent, GaugeParser parser) {
        FromTo fromTo = new FromTo(node, singletonList(parent));
        try {
            return parser.parseAdd(node, parent);
        } catch (WrongGaugeParserException e) {
        }
        return fromTo;
    }

    public static FromTo many(Node node, Component parent, GaugeParser parser) {
        FromTo parse = new FromTo(node, singletonList(parent));
        try {
            while (true) {
                parse = parser.parse(node);
                node = parse.from;
                parent.addAllComponents(parse.to);
            }
        } catch (WrongGaugeParserException e) {
        }
        return parse;
    }

    public static FromTo many1(Node node, Component parent, GaugeParser parser) {
        FromTo parse = parser.parse(node);
        many(parse.from, parent, parser);
        return parse;
    }

    public static FromTo manyTill(Node node, Component parent, GaugeParser parser1, GaugeParser parser2) {
        FromTo many = many(node, parent, parser1);
        parser2.parse(node);
        return many;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    public static final SpecificationParser specificationParser = new SpecificationParser();
    public static final SpecificationHeadingParser specificationHeadingParser = new SpecificationHeadingParser();
    public static final ScenarioHeadingParser scenarioHeadingParser = new ScenarioHeadingParser();
    public static final CommentParser commentParser = new CommentParser();
    public static final CommentTagParser commentTagParser = new CommentTagParser();
    public static final TagsParser tagsParser = new TagsParser();
    public static final ScenarioParser scenarioParser = new ScenarioParser();
    public static final ContextStepsParser contextStepsParser = new ContextStepsParser();
    public static final TeardownStepsParser teardownStepsParser = new TeardownStepsParser();
    public static final StepsParser stepsParser = new StepsParser();
    public static final StepParser stepParser = new StepParser();
}
