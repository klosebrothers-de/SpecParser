package de.klosebrothers.specparser.gauge.datastructure;

import java.util.List;

import static de.klosebrothers.specparser.gauge.datastructure.Util.findAll;
import static de.klosebrothers.specparser.gauge.datastructure.Util.findFirst;

public class Specification extends Component {
    public List<Scenario> getScenarios() {
        return findAll(branches, Scenario.class);
    }

    public List<Tag> getTags() {
        return findFirst(branches, Tags.class).getTags();
    }

    public List<Step> getTearDownSteps() {
        return findAll(findFirst(branches, TearDownSteps.class).branches, Step.class);
    }

    public List<Step> getContextSteps() {
        return findAll(findFirst(branches, ContextSteps.class).branches, Step.class);
    }

    public List<Comment> getComments() {
        return findAll(branches, Comment.class);
    }

    @Override
    public String toMD() {
        return "";
    }
}
