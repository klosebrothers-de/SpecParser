package de.klosebrothers.specparser.gauge;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.*;

public class Scenario implements HasTagsAndComment {

    private String heading;
    private String comment;

    private List<Tag> tags = new ArrayList<>();

    private List<Step> steps = new ArrayList<>();

    public void setHeading(String heading) {
        this.heading = heading;
    }

    @Override
    public void addTag(String tag) {
        tags.add(new Tag(tag));
    }

    @Override
    public void setComment(String comment) {
        this.comment = comment;
    }

    public void addStep(String stepText) {
        steps.add(new Step(stepText));
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

    public List<Step> getSteps() {
        return steps;
    }

    public String getStepsAsStringJoinedBy(String joining) {
        return steps.stream().map(Step::getStepText).collect(Collectors.joining(joining));
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Scenario)) {
            return false;
        }
        Scenario scenario = (Scenario) obj;
        return scenario.heading.equals(heading) &&
                scenario.comment == null ? comment == null : scenario.comment.equals(comment) &&
                steps.containsAll(scenario.getSteps()) &&
                steps.size()== scenario.steps.size();
    }

    @Override
    public int hashCode() {
        return Objects.hash(heading, comment, tags, steps);
    }

}
