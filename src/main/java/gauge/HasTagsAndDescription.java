package gauge;

import java.util.List;

public interface HasTagsAndDescription {
    void addTag(String s);
    void setDescription(String description);
    List<Tag> getTags();
    String getDescription();
}
