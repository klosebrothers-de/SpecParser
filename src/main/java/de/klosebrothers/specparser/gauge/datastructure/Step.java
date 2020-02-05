package de.klosebrothers.specparser.gauge.datastructure;

import java.util.Objects;

public class Step extends Component {
    private String step;

    public Step(String step) {
        this.step = step;
    }

    public String getStep() {
        return step;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Step step1 = (Step) o;
        return step.equals(step1.step);
    }

    @Override
    public int hashCode() {
        return Objects.hash(step);
    }
}
