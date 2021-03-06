package de.klosebrothers.specparser.gauge.parser;

import de.klosebrothers.specparser.gauge.datastructure.Tags;
import org.commonmark.node.Node;
import org.commonmark.node.Paragraph;
import org.commonmark.node.Text;

import java.util.Arrays;

import static de.klosebrothers.specparser.gauge.datastructure.Tags.tagsStringToTagsArray;
import static java.util.Collections.singletonList;

public class TagsParser extends GaugeParser {
    @Override
    public FromTo parse(Node node) {
        if (!(node instanceof Paragraph))
            throw new WrongGaugeParserException(node, this);

        String tagString = ((Text) node.getFirstChild()).getLiteral();
        String[] tags = tagsStringToTagsArray(tagString);
        return new FromTo(node.getNext(), singletonList(new Tags(tags)));
    }
}
