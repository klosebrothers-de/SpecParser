package de.klosebrothers.specparser.gauge.datastructure;

import java.util.Objects;

public class Comment extends Component {
    private String comment;

    public Comment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment1 = (Comment) o;
        return comment.equals(comment1.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(comment);
    }

    @Override
    public String toMD() {
        return comment+"\n";
    }
}
