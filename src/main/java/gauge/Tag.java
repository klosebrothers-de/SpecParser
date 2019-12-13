package gauge;

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
        return ((Tag) obj).tagName.equals(tagName);
    }
}
