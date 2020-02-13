package de.klosebrothers.specparser.gauge.datastructure;

import lombok.Getter;
import lombok.Setter;

public class SpecificationHeading extends Component {

    @Getter @Setter
    private String heading;

    public SpecificationHeading(String heading) {
        this.heading = heading;
    }

    @Override
    public String toMD() {
        return "# " + heading + "\n";
    }
}
