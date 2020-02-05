package de.klosebrothers.specparser.gauge.datastructure;

import java.util.List;

import static de.klosebrothers.specparser.gauge.datastructure.Util.findAll;
import static de.klosebrothers.specparser.gauge.datastructure.Util.findFirst;

public class Scenario extends Component {
    public String getHeading() {
        return findFirst(branches, Heading.class).getHeading();
    }

    public List<Step> getSteps() {
        Steps first = findFirst(branches, Steps.class);
        return findAll(first.branches, Step.class);
    }

    public Steps getStepsNode(){
        return findFirst(branches, Steps.class);
    }
}
