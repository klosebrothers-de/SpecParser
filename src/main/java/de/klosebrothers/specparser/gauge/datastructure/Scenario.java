package de.klosebrothers.specparser.gauge.datastructure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static de.klosebrothers.specparser.gauge.datastructure.Util.*;
import static java.util.Collections.unmodifiableList;

public class Scenario extends Component {

    public void setHeading(String heading) {
        findFirst(branches, ScenarioHeading.class)
                .ifPresent((a) -> branches.remove(a));
        branches.add(0, new ScenarioHeading(heading));
    }

    public Optional<Tags> getTags(){
        return findFirst(branches, Tags.class);
    }

    public Optional<ScenarioHeading> getHeading() {
        return findFirst(branches, ScenarioHeading.class);
    }

    public Optional<Steps> getStepsNode() {
        return findFirst(branches, Steps.class);
    }

    public void removeStep(Step step){
        findFirst(branches, Steps.class).ifPresent( steps -> steps.branches.remove(step));
    }

    public void removeAllSteps(List<Step> steps){
        findFirst(branches, Steps.class).ifPresent( stepsNode -> stepsNode.branches.removeAll(steps));
    }

    public List<Step> getSteps() {
        return unmodifiableList(findFirst(branches, Steps.class)
                .map(steps -> findAll(steps.branches, Step.class))
                .orElse(new ArrayList<>()));
    }

    @Override
    public String toMD() {
        return "\n";
    }

    public void addStep(String stepText) {
        Steps steps = addIfNotPresent(branches, Steps.class);
        steps.branches.add(new Step(stepText));
    }

    public void addStep(int index, String stepText) {
        Steps steps = addIfNotPresent(branches, Steps.class);
        steps.branches.add(index, new Step(stepText));
    }

    public void addAllSteps(List<String> stringSteps){
        List<Step> steps = stringSteps.stream().map(Step::new).collect(Collectors.toList());
        Steps stepsNode = addIfNotPresent(branches, Steps.class);
        stepsNode.branches.addAll(steps);
    }

    public void addAllSteps(int index, List<String> stringSteps){
        List<Step> steps = stringSteps.stream().map(Step::new).collect(Collectors.toList());
        Steps stepsNode = addIfNotPresent(branches, Steps.class);
        stepsNode.branches.addAll(index, steps);
    }
}
