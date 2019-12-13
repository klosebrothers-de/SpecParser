package gauge;

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
        return ((Step) obj).stepText.equals(stepText);
    }
}
