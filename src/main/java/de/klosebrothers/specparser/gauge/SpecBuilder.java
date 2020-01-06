package de.klosebrothers.specparser.gauge;

import java.util.*;
import java.util.stream.*;

public class SpecBuilder {
    public static String fromSpecification(Specification specification) {
        StringBuilder sb = new StringBuilder();
        heading(sb, specification);
        tags(sb, specification);
        comment(sb, specification);
        contextSteps(sb, specification);
        scenarios(sb, specification);
        tearDownSteps(sb, specification);
        return sb.toString();
    }

    private static void tearDownSteps(StringBuilder sb, Specification specification) {
        appendStepList(sb,specification.getTearDownSteps());
    }

    private static void contextSteps(StringBuilder sb, Specification specification) {
        appendStepList(sb,specification.getContextSteps());
    }

    private static void appendStepList(StringBuilder sb, List<Step> list) {
        list.forEach(step -> {
            sb.append("* ").append(step.getStepText()).append("\n");
        });
        sb.append("\n\n");
    }

    private static void scenarios(StringBuilder sb, Specification specification) {
        specification.getScenarios().forEach(scenario -> {
            scenarioMD(sb, scenario);
        });
    }

    private static void scenarioMD(StringBuilder sb, Scenario scenario) {
        scenarioHeadingMD(sb, scenario);
        tags(sb, scenario);
        comment(sb, scenario);
        stepsMD(sb, scenario);
    }

    private static void stepsMD(StringBuilder sb, Scenario scenario) {
        appendStepList(sb, scenario.getSteps());
    }

    private static void scenarioHeadingMD(StringBuilder sb, Scenario scenario) {
        sb.append("## ").append(scenario.getHeading()).append("\n");
    }

    private static void comment(StringBuilder sb, HasTagsAndComment specification) {
        if (specification.getComment() == null) {
            return;
        }
        sb
                .append(specification.getComment())
                .append("\n\n");
    }

    private static void tags(StringBuilder sb, HasTagsAndComment specification) {
        List<Tag> tags = specification.getTags();
        if (tags.isEmpty()) {
            return;
        }

        sb
                .append("Tags: ")
                .append(tags.stream().map(Tag::getTagName).collect(Collectors.joining(",")))
                .append("\n\n");
    }

    private static StringBuilder heading(StringBuilder sb, Specification specification) {
        return sb.append("# ").append(specification.getHeading()).append("\n");
    }
}
