package de.klosebrothers.specparser.gauge.datastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static de.klosebrothers.specparser.gauge.datastructure.Util.*;
import static java.util.Collections.unmodifiableList;

public class Specification extends Component {
    public List<Scenario> getScenarios() {
        return unmodifiableList(findAll(branches, Scenario.class));
    }

    public List<Tag> getTags() {
        return getOrAddIfNotPresent(branches, Tags.class).getTags();
    }

    public List<Step> getTearDownSteps() {
        return unmodifiableList(findFirst(branches, TearDownSteps.class)
                .map(tearDownSteps -> findAll(tearDownSteps.branches, Step.class))
                .orElse(new ArrayList<>()));
    }

    public List<Step> getContextSteps() {
        return unmodifiableList(findFirst(branches, ContextSteps.class)
                .map(tearDownSteps -> findAll(tearDownSteps.branches, Step.class))
                .orElse(new ArrayList<>()));
    }

    public List<Comment> getComments() {
        return unmodifiableList(findAll(branches, Comment.class));
    }

    @Override
    public String toMD() {
        return "";
    }

//    public Scenario getScenariosNode() {
//        return null;
//    }

    public Optional<SpecificationHeading> getHeading() {
        return findFirst(branches, SpecificationHeading.class);
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

    public void addTearDownStep(String tearDownStepText) {
        TearDownSteps tearDownSteps = getOrAddIfNotPresent(branches, TearDownSteps.class);
        tearDownSteps.branches.add(new Step(tearDownStepText));
    }

    public void addContextStep(String contextStepText) {
        ContextSteps contextSteps = getOrAddIfNotPresent(branches, ContextSteps.class);
        contextSteps.branches.add(new Step(contextStepText));
    }

}
