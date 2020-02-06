package de.klosebrothers.specparser.gauge.datastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static de.klosebrothers.specparser.gauge.datastructure.Util.*;

public class Scenario extends Component {
    public Optional<String> getHeading() {
        return findFirst(branches, ScenarioHeading.class).map(ScenarioHeading::getHeading);
    }

    public List<Step> getSteps() {
        return findFirst(branches, Steps.class)
                .map(steps -> findAll(steps.branches, Step.class))
                .orElse(new ArrayList<>());
    }

    public Optional<Steps> getStepsNode() {
        return findFirst(branches, Steps.class);
    }

    @Override
    public String toMD() {
        return "\n";
    }

    public void addStep(String stepText) {
        Steps steps = addIfNotPresent(branches, Steps.class);
        steps.branches.add(new Step(stepText));
    }

}
