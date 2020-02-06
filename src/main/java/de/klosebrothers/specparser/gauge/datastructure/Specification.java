package de.klosebrothers.specparser.gauge.datastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static de.klosebrothers.specparser.gauge.datastructure.Util.findAll;
import static de.klosebrothers.specparser.gauge.datastructure.Util.findFirst;

public class Specification extends Component {
    public List<Scenario> getScenarios() {
        return findAll(branches, Scenario.class);
    }

    public List<Tag> getTags() {
        return findFirst(branches, Tags.class).map(Tags::getTags).orElse(new ArrayList<>());
    }

    public List<Step> getTearDownSteps() {
        return findFirst(branches, TearDownSteps.class)
                .map(tearDownSteps -> findAll(tearDownSteps.branches, Step.class))
                .orElse(new ArrayList<>());
    }

    public List<Step> getContextSteps() {
        return findFirst(branches, ContextSteps.class)
                .map(tearDownSteps -> findAll(tearDownSteps.branches, Step.class))
                .orElse(new ArrayList<>());
    }

    public List<Comment> getComments() {
        return findAll(branches, Comment.class);
    }

    @Override
    public String toMD() {
        return "";
    }

//    public Scenario getScenariosNode() {
//        return null;
//    }

    public Optional<String> getHeading() {
        return findFirst(branches, SpecificationHeading.class).map(SpecificationHeading::getHeading);
    }

    public void setHeading(String heading) {
        findFirst(branches, SpecificationHeading.class)
                .ifPresent((a) -> branches.remove(a));
        branches.add(0, new SpecificationHeading(heading));
    }

    public void addScenario(Scenario scenario) {
        Optional<TearDownSteps> maybeTearDownSteps = findFirst(branches, TearDownSteps.class);
        if (maybeTearDownSteps.isPresent()) {
            branches.add(branches.size() - 1, scenario);
        } else {
            branches.add(scenario);
        }
    }


}
