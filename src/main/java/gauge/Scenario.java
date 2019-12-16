package gauge;

import java.util.ArrayList;
import java.util.List;

public class Scenario implements HasTagsAndDescription {

    private String heading;
    private String description;
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
    public void setDescription(String description) {
        this.description = description;
    }

    public void addStep(String stepText) {
        steps.add(new Step(stepText));
    }

    public String getHeading() {
        return heading;
    }

    public String getDescription() {
        return description;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public List<Step> getSteps() {
        return steps;
    }

    @Override
    public boolean equals(Object obj) {
        Scenario scenario = (Scenario) obj;
        return scenario.heading.equals(heading) &&
                scenario.description == null ? description == null : scenario.description.equals(description) &&
                steps.containsAll(scenario.getSteps()) &&
                steps.size()== scenario.steps.size();
    }
}
