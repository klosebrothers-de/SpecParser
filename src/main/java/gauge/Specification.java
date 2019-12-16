package gauge;

import java.util.ArrayList;
import java.util.List;

public class Specification implements HasTagsAndComment {
    private String heading;
    private String comment;
    private String commentTearDown;
    private List<Tag> tags = new ArrayList<>();
    private List<Scenario> scenarios = new ArrayList<>();
    private List<Step> tearDownSteps = new ArrayList<>();
    private List<Step> contextSteps = new ArrayList<>();

    public void setHeading(String heading) {
        this.heading = heading;
    }

    @Override
    public void addTag(String s) {
        tags.add(new Tag(s));
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void addScenario(Scenario scenario) {
        scenarios.add(scenario);
    }

    public String getHeading() {
        return heading;
    }

    public String getComment() {
        return comment;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public List<Scenario> getScenarios() {
        return scenarios;
    }

    public List<Step> getTearDownSteps() {
        return tearDownSteps;
    }

    public List<Step> getContextSteps() {
        return contextSteps;
    }

    @Override
    public boolean equals(Object obj) {
        Specification specification = (Specification) obj;
        return specification.getHeading().equals(heading) &&
                specification.getComment() == null ? comment == null : specification.comment.equals(comment) &&
                specification.getCommentTearDown() == null ? commentTearDown == null : specification.commentTearDown.equals(commentTearDown) &&
                scenarios.containsAll(specification.getScenarios()) &&
                scenarios.size()  == specification.scenarios.size();
    }

    public void addContextStep(Step step) {
        contextSteps.add(step);
    }

    public void setCommentTearDown(String literal) {
        commentTearDown = literal;
    }

    public String getCommentTearDown() {
        return commentTearDown;
    }

    public void addTearDownStep(Step step) {
        tearDownSteps.add(step);
    }
}
