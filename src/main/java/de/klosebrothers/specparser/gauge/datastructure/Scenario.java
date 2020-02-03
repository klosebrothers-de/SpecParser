package de.klosebrothers.specparser.gauge.datastructure;

import java.util.List;

public class Scenario extends Component {
    public String getHeading() {
        return null;
    }

    public List<Step> getSteps() {
        return null;
    }

    public Steps getStepsNode(){
        return (Steps) branches.stream().filter(c -> c instanceof Steps).findFirst().orElseThrow();
    }
}
