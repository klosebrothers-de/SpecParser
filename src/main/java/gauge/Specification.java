package gauge;

import java.util.ArrayList;
import java.util.List;

public class Specification implements HasTagsAndDescription {
    private String heading;
    private String description;
    private String descriptionTearDown;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public void addScenario(Scenario scenario) {
        scenarios.add(scenario);
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
                specification.getDescription() == null ? description == null : specification.description.equals(description) &&
                specification.getDescriptionTearDown() == null ? descriptionTearDown == null : specification.descriptionTearDown.equals(descriptionTearDown) &&
                scenarios.containsAll(specification.getScenarios()) &&
                scenarios.size()  == specification.scenarios.size();
    }

    public void addContextStep(Step step) {
        contextSteps.add(step);
    }

    public void setDescriptionTearDown(String literal) {
        descriptionTearDown = literal;
    }

    public String getDescriptionTearDown() {
        return descriptionTearDown;
    }

    public void addTearDownStep(Step step) {
        tearDownSteps.add(step);
    }
}
