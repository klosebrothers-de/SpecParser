package de.klosebrothers.specparser.gauge.parser;

import de.klosebrothers.specparser.gauge.datastructure.Comment;
import de.klosebrothers.specparser.gauge.datastructure.Component;
import de.klosebrothers.specparser.gauge.datastructure.Tags;
import org.commonmark.node.Node;
import org.commonmark.node.Paragraph;
import org.commonmark.node.SoftLineBreak;
import org.commonmark.node.Text;

import java.util.ArrayList;

import static java.util.Collections.singletonList;

public class CommentParser extends GaugeParser {
    @Override
    public FromTo parse(Node node) {
        if (!(node instanceof Paragraph)) {
            throw new WrongGaugeParserException(node, this);
        }
        ArrayList<Component> result = new ArrayList<>();
        Node itNode = node.getFirstChild();
        while (itNode != null) {
            if (!(itNode instanceof SoftLineBreak)) {
                String literal = ((Text) itNode).getLiteral();
                if (literal.startsWith("Tags: ")) {
                    throw new WrongGaugeParserException(itNode, this);
                } else {
                    result.add(new Comment(literal));
                }
            }
            itNode = itNode.getNext();
        }
        return new FromTo(node.getNext(), result);
    }
}
