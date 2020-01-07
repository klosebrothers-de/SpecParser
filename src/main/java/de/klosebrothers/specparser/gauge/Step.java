package de.klosebrothers.specparser.gauge;

import java.util.Objects;

public class Step {
    private String stepText;

    public Step(String stepText) {
        this.stepText = stepText;
    }

    public String getStepText() {
        return stepText;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Step)) {
            return false;
        }
        return ((Step) obj).stepText.equals(stepText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stepText);
    }
}
