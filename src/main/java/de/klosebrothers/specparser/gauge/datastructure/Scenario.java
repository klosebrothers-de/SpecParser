package de.klosebrothers.specparser.gauge.datastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static de.klosebrothers.specparser.gauge.datastructure.Util.findAll;
import static de.klosebrothers.specparser.gauge.datastructure.Util.findFirst;

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

}
