package de.klosebrothers.specparser.gauge.datastructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Tags extends Component {
    private List<Tag> tags = new ArrayList<>();

    public Tags() {
        this(new String[0]);
    }

    public Tags(String[] tags) {
        List<Tag> collect = Arrays.stream(tags).map(Tag::new).collect(Collectors.toList());
        this.tags.addAll(collect);
    }

    public List<Tag> getTags() {
        return tags;
    }

    @Override
    public String toMD() {
        return "Tags: " + tags.stream().map(Tag::getTag).collect(Collectors.joining(", ")) + "\n\n";
    }

    public static  String[] tagsStringToTagsArray(String tagString) {
        return Arrays.stream(tagString.substring(6).split(","))
                .map(s -> s.replaceAll("\\s", "")).toArray(String[]::new);
    }
}
