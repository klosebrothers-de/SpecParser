package de.klosebrothers.specparser.gauge.parser;

import de.klosebrothers.specparser.gauge.datastructure.Comment;
import org.commonmark.node.Heading;
import org.commonmark.node.Node;
import org.commonmark.node.Paragraph;
import org.commonmark.node.Text;

public class CommentParser extends GaugeParser {
    @Override
    public FromTo parse(Node node) {
        String commentText = ((Text) node.getFirstChild()).getLiteral();
        if (!(node instanceof Paragraph) || commentText.startsWith("Tags: ")) {
            throw new WrongParserException(node, this);
        }

        Comment comment = new Comment(commentText);
        return new FromTo(node.getNext(), comment);
    }
}
