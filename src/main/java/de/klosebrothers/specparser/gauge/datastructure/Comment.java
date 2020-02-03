package de.klosebrothers.specparser.gauge.datastructure;

public class Comment extends Component {
    private String comment;

    public Comment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }
}
