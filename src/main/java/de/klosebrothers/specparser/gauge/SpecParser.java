package de.klosebrothers.specparser.gauge;

import org.commonmark.node.*;
import org.commonmark.parser.Parser;

import java.util.Arrays;
import java.util.function.BiFunction;

public class SpecParser {

    public static Specification toSpecification(String spec) {
        Parser parser = Parser.builder().build();
        Node node = parser.parse(spec);
        return toSpecification(node);
    }

    private static Specification toSpecification(Node node) {
        Specification specification = new Specification();
        node = headingSpec(node.getFirstChild(), specification);
        node = maybe(SpecParser::tags, node, specification);
        node = maybe(SpecParser::comment, node, specification);
        node = maybe(SpecParser::contextSteps, node, specification);
        node = oneOrMore(SpecParser::scenario, node, specification);
        node = maybe(SpecParser::tearDownSteps, node, specification);
        return specification;
    }

    private static Node commentTearDown(Node node, Specification specification) {
        specification.setCommentTearDown(((Text) node.getFirstChild()).getLiteral());
        return node.getNext();
    }

    private static Node contextSteps(Node node, Specification specification) {
        oneOrMore(SpecParser::contextStep, ((BulletList) node).getFirstChild(), specification);
        return node.getNext();
    }

    private static Node contextStep(Node list, Specification specification) {
        String stepText = ((Text) list.getFirstChild().getFirstChild()).getLiteral();
        specification.addContextStep(new Step(stepText));
        return list.getNext();
    }

    private static Node tearDownSteps(Node node, Specification specification) {
        node = ((ThematicBreak) node).getNext();
        node = maybe(SpecParser::commentTearDown, node, specification);
        oneOrMore(SpecParser::tearDownStep, node.getFirstChild(), specification);
        return node.getNext();
    }

    private static Node tearDownStep(Node node, Specification specification) {
        String stepText = ((Text) node.getFirstChild().getFirstChild()).getLiteral();
        specification.addTearDownStep(new Step(stepText));
        return node.getNext();
    }

    private static Node scenario(Node node, Specification specification) {
        Scenario scenario = new Scenario();
        node = headingScenario(node, scenario);
        node = maybe(SpecParser::tags, node, scenario);
        node = maybe(SpecParser::comment, node, scenario);
        node = steps(node, scenario);
        specification.addScenario(scenario);
        return node;
    }

    private static Node steps(Node node, Scenario scenario) {
        oneOrMore(SpecParser::step, node.getFirstChild(), scenario);
        return node.getNext();
    }

    private static Node step(Node node, Scenario scenario) {
        String stepText = ((Text) node.getFirstChild().getFirstChild()).getLiteral();
        scenario.addStep(stepText);
        return node.getNext();
    }

    private static Node comment(Node node, HasTagsAndComment hasTagsAndComment) {
        String comment = ((Text) node.getFirstChild()).getLiteral();
        hasTagsAndComment.setComment(comment);
        return node.getNext();
    }

    private static Node tags(Node node, HasTagsAndComment hasTagsAndComment) {
        String tags = ((Text) node.getFirstChild()).getLiteral();
        if (!tags.startsWith("Tags: ")) {
            return node;
        }
        Arrays.stream(tags.substring(6).split(","))
                .map(String::trim)
                .forEach(hasTagsAndComment::addTag);
        return node.getNext();
    }

    private static Node headingSpec(Node node, Specification specification) {
        assert ((Heading) node).getLevel() == 1;
        specification.setHeading(((Text) (node.getFirstChild())).getLiteral());
        return node.getNext();
    }

    private static Node headingScenario(Node node, Scenario scenario) {
        assert ((Heading) node).getLevel() == 2;
        scenario.setHeading(((Text) (node.getFirstChild())).getLiteral());
        return node.getNext();
    }

    //func
    private static <A> Node maybe(BiFunction<Node, A, Node> f, Node node, A a) {
        try {
            return f.apply(node, a);
        } catch (Exception e) {
            return node;
        }
    }

    private static <A> Node oneOrMore(BiFunction<Node, A, Node> f, Node node, A a) {
        node = f.apply(node, a);
        try {
            //noinspection InfiniteLoopStatement
            while (true) {
                node = f.apply(node, a);
            }
        } catch (Exception e) {
            return node;
        }
    }

}