package de.klosebrothers.specparser.gauge;

import java.util.Objects;

public class Tag {
    private String tagName;

    public Tag(String tagName) {
        this.tagName = tagName;
    }

    public String getTagName() {
        return tagName;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Tag)) {
            return false;
        }
        return ((Tag) obj).tagName.equals(tagName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tagName);
    }
}
