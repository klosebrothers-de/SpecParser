package gauge;

import java.util.List;

public interface HasTagsAndComment {
    void addTag(String s);
    void setComment(String comment);
    List<Tag> getTags();
    String getComment();
}
