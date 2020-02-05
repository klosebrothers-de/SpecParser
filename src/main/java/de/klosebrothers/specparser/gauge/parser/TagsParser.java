package de.klosebrothers.specparser.gauge.parser;

import de.klosebrothers.specparser.gauge.datastructure.Tags;
import org.commonmark.node.Node;
import org.commonmark.node.Paragraph;
import org.commonmark.node.Text;

public class TagsParser extends GaugeParser {
    @Override
    public FromTo parse(Node node) {
        if (!(node instanceof Paragraph))
            throw new WrongParserException(node, this);

        String tagString = ((Text) node.getFirstChild()).getLiteral();
        String[] tags = tagString.substring(6).split(",");
        return new FromTo(node.getNext(), new Tags(tags));
    }
}
