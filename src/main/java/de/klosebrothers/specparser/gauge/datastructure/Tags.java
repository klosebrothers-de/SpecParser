package de.klosebrothers.specparser.gauge.datastructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Tags extends Component {
    private List<Tag> tags = new ArrayList<>();

    public Tags(String[] tags) {
        List<Tag> collect = Arrays.stream(tags).map(Tag::new).collect(Collectors.toList());
        this.tags.addAll(collect);
    }

    public List<Tag> getTags() {
        return tags;
    }

    @Override
    public String toMD() {
        return "Tags: " + tags.stream().map(Tag::getTag).collect(Collectors.joining(", ")) + "\n";
    }
}
