package gauge;

import java.util.ArrayList;
import java.util.List;

public class Specification implements HasTagsAndDescription {
    private String heading;
    private String description;
    private List<Tag> tags = new ArrayList<>();
    private List<Scenario> scenarios= new ArrayList<>();

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
}
