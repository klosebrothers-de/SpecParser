package de.klosebrothers.specparser.gauge.datastructure;

public class ScenarioHeading extends Component {
    private String heading;

    public ScenarioHeading(String heading) {
        this.heading = heading;
    }

    public String getHeading() {
        return heading;
    }

    @Override
    public String toMD() {
        return "## " + heading + "\n";
    }
}
