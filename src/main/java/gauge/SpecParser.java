package gauge;

import org.commonmark.node.Heading;
import org.commonmark.node.Node;
import org.commonmark.node.Text;
import org.commonmark.parser.Parser;

import java.util.Arrays;
import java.util.function.BiFunction;

public class SpecParser {

    public static Specification parse(String spec){
        Parser parser = Parser.builder().build();
        Node node = parser.parse(spec);
        return parse(node);
    }

    private static Specification parse(Node node) {
        Specification specification = new Specification();
        node = headingSpec(node.getFirstChild(), specification);
        node = maybe(SpecParser::tags, node, specification);
        node = maybe(SpecParser::description, node, specification);
        oneOrMore(SpecParser::scenario, node, specification);
        return specification;
    }

    private static Node scenario(Node node, Specification specification) {
        Scenario scenario = new Scenario();
        node = headingScenario(node, scenario);
        node = maybe(SpecParser::tags, node, scenario);
        node = maybe(SpecParser::description, node, scenario);
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

    private static Node description(Node node, HasTagsAndDescription hasTagsAndDescription) {
        String description = ((Text) node.getFirstChild()).getLiteral();
        hasTagsAndDescription.setDescription(description);
        return node.getNext();
    }

    private static Node tags(Node node, HasTagsAndDescription hasTagsAndDescription) {
        String tags = ((Text) node.getFirstChild()).getLiteral();
        if (!tags.startsWith("Tags: ")) {
            return node;
        }
        Arrays.stream(tags.substring(6).split(","))
                .map(String::trim)
                .forEach(hasTagsAndDescription::addTag);
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
