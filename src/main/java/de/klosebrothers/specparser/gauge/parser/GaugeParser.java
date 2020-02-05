package de.klosebrothers.specparser.gauge.parser;

import de.klosebrothers.specparser.gauge.datastructure.Component;
import de.klosebrothers.specparser.gauge.datastructure.Specification;
import org.commonmark.node.Node;

public abstract class GaugeParser {
    protected abstract FromTo parse(Node node);

    public FromTo parseAdd(Node node, Component parent) {
        FromTo parse = parse(node);
        parent.addComponent(parse.to);
        return parse;
    }

    public static FromTo maybe(Node node, Component parent, GaugeParser parser) {
        FromTo fromTo = new FromTo(node, parent);
        try {
            return parser.parseAdd(node, parent);
        } catch (Exception e) {

        }
        return fromTo;
    }

    public static FromTo oneOf(Node node, Component parent, GaugeParser... parsers) {
        for (GaugeParser parser : parsers) {
            try {
                FromTo parse = parser.parse(node);
                parent.addComponent(parse.to);
                return parse;
            } catch (GaugeParserException e) {
                throw e;
            } catch (Exception e) {
                continue;
            }
        }
        throw new GaugeParserException(node, "Maybe Error");
    }

    public static FromTo many(Node node, Component parent, GaugeParser parser) {
        FromTo parse = new FromTo(node, parent);
        try {
            while (true) {
                parse = parser.parse(node);
                node = parse.from;
                parent.addComponent(parse.to);
            }
        } catch (Exception e) {
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

    public static final SpecificationParser specificationParser = new SpecificationParser();
    public static final HeadingParser headingParser = new HeadingParser();
    public static final CommentParser commentParser = new CommentParser();
    public static final TagsParser tagsParser = new TagsParser();
    public static final ScenarioParser scenarioParser = new ScenarioParser();
    public static final ContextStepsParser contextStepsParser = new ContextStepsParser();
    public static final TeardownStepsParser teardownStepsParser = new TeardownStepsParser();
    public static final StepsParser stepsParser = new StepsParser();
    public static final StepParser stepParser = new StepParser();
}
