package de.klosebrothers.specparser.gauge;

import de.klosebrothers.specparser.gauge.datastructure.*;
import de.klosebrothers.specparser.gauge.parser.GaugeParserException;
import de.klosebrothers.specparser.gauge.parser.WrongGaugeParserException;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static de.klosebrothers.specparser.gauge.SpecBuilder.toSpecification;
import static de.klosebrothers.specparser.gauge.TestEnvironment.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class SpecParserTest {

    //Positive Tests


    @Test
    void shouldReplaceHeadingFromSpec() {
        Specification specification = toSpecification(gauge);
        String newHeading = "new Heading";

        specification.setHeading(newHeading);

        assertThat(specification.getHeading().get())
                .isEqualTo(newHeading);
    }

    @Test
    void shouldAddTearDownStep() {
        Specification specification = toSpecification(gauge);
        String stepText = "Tear me down";
        specification.addTearDownStep(stepText);
        assertThat(specification.getTearDownSteps()).contains(new Step(stepText));
    }

    @Test
    void shouldAddContextStep() {
        Specification specification = toSpecification(gauge);
        String stepText = "Context me plz";
        specification.addContextStep(stepText);
        assertThat(specification.getContextSteps()).contains(new Step(stepText));
    }

    @Test
    void addScenarioToSpecification() {
        Scenario scenario = new Scenario();
        Specification specification = new Specification();

        specification.addScenario(scenario);

        assertThat(specification.getScenarios())
                .containsExactly(scenario);
    }

    @Test
    void addStepsToScenario() {
        Scenario scenario = new Scenario();
        String stepText = "some Step";

        scenario.addStep(stepText);

        assertThat(scenario.getSteps()).containsExactly(new Step(stepText));
    }

    @Test
    void gaugeShouldHaveThisComment() {
        Specification specification = toSpecification(gauge);
        assertThat(specification.getComments())
                .containsExactly(
                        new Comment("The admin user must be able to search for available products on the search page"));
    }

    @Test
    void gaugeShouldParseWithCommentFirst() {
        String comment = "This is a Comment plz parse mee :)\n";
        Specification specification = toSpecification(comment + gauge);
        assertThat(specification.getComments())
                .containsExactly(
                        new Comment("This is a Comment plz parse mee :)"),
                        new Comment("The admin user must be able to search for available products on the search page"));
    }

    @Test
    void gaugeCanHaveCommentsBetweenSteps() {
        String gaugeWithCommentInSteps = gauge.replace("* User must be logged in as \"admin\"\n", "* User must be logged in as \"admin\"\n\nThis is a Comment\n\n");
        System.out.println(gaugeWithCommentInSteps);
        Specification specification = toSpecification(gauge);
        Optional<Steps> stepsNode = specification.getScenarios().get(0).getStepsNode();

    }

    @Test
    void smallGaugeShouldHaveTagsSearchAdmin() {
        Specification specification = toSpecification(gaugeSmall);
        List<Tag> tags = specification.getTags();
        assertThat(tags)
                .containsExactly(
                        new Tag("search"),
                        new Tag("admin"));
    }

    @Test
    void smallGaugeHasOneScenarioNamedSuccessfulSearch() {
        Specification specification = toSpecification(gaugeSmall);
        Optional<String> heading = specification.getScenarios().get(0).getHeading();
        assertThat(heading).isPresent();
        assertThat(heading.get())
                .isEqualTo("Successful search");
    }

    @Test
    void gaugeHasOneScenarioWith4Steps() {
        Specification specification = toSpecification(gauge);
        assertThat(specification.getScenarios().get(0).getSteps())
                .containsExactly(
                        new Step("User must be logged in as \"admin\""),
                        new Step("Open the product search page"),
                        new Step("Search for product \"Cup Cakes\""),
                        new Step("\"Cup Cakes\" should show up in the search results"));
    }

    @Test
    void smallGaugeHasOneScenarioWith2Steps() {
        Specification specification = toSpecification(gaugeSmall);
        assertThat(specification.getScenarios().get(0).getSteps())
                .containsExactly(
                        new Step("User must be logged in as \"admin\""),
                        new Step("\"Cup Cakes\" should show up in the search results"));
    }

    @Test
    void gaugeHas2Scenarios() {
        Specification specification = toSpecification(gauge);
        assertThat(specification.getScenarios().size())
                .isEqualTo(2);
    }

    @Test
    void shouldParseGaugeWithTearDownAndContextSteps() {
        Specification specification = toSpecification(gaugeWithTearDown);
        List<Step> tearDownSteps = specification.getTearDownSteps();
        List<Step> contextSteps = specification.getContextSteps();

        assertThat(contextSteps)
                .containsExactly(
                        new Step("Sign up for user \"mike\""),
                        new Step("Log in as \"mike\""));

        assertThat(tearDownSteps)
                .containsExactly(
                        new Step("Logout user \"mike\""),
                        new Step("Delete user \"mike\""));

    }

    // TODO: 13.12.19 Replace Exceptions w/ GaugeExceptions
    //Negative Tests
    @Test
    void gaugeWithoutHeading() {
        String search_specification = deleteLineWith(gauge, "Search specification");
        assertThatExceptionOfType(WrongGaugeParserException.class)
                .isThrownBy(() -> toSpecification(search_specification));
    }

    @Test
    void smallGaugeWithoutSteps() {
        String gaugeWithoutSteps = deleteLineWith(gaugeSmall, "User must be logged in as", "\"Cup Cakes\" should show up in the");
        assertThatExceptionOfType(GaugeParserException.class)
                .isThrownBy(() -> toSpecification(gaugeWithoutSteps))
        .withMessage("Tried to parse null with parser \"StepsParser\" but failed with error message: \"Can't parse nullpointer, reached end of file?\"");
    }

}
