package de.klosebrothers.specparser.gauge.datastructure;

public class SpecificationHeading extends Component {
    private String heading;

    public SpecificationHeading(String heading) {
        this.heading = heading;
    }

    public String getHeading() {
        return heading;
    }

    @Override
    public String toMD() {
        return "# " + heading + "\n";
    }
}
