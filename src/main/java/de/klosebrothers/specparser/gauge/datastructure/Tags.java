package de.klosebrothers.specparser.gauge.datastructure;

import java.util.ArrayList;
import java.util.List;

public class Tags extends Component{
    private List<Tag> tags = new ArrayList<>();

    public List<Tag> getTags() {
        return tags;
    }
}
