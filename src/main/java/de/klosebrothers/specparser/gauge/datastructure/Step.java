package de.klosebrothers.specparser.gauge.datastructure;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

public class Step extends Component {
    @Getter @Setter
    private String stepText;

    public Step(String stepText) {
        this.stepText = stepText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Step step1 = (Step) o;
        return stepText.equals(step1.stepText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stepText);
    }

    @Override
    public String toMD() {
        return "* " + stepText + "\n";
    }

    @Override
    public String toString() {
        return "Step{" +
                "stepText='" + stepText + '\'' +
                '}';
    }
}
