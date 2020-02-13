package de.klosebrothers.specparser.gauge.datastructure;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

public class Comment extends Component {

    @Setter @Getter private String comment;

    public Comment(String comment) {
        this.comment = comment;
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
