package de.klosebrothers.specparser.gauge;

import de.klosebrothers.specparser.gauge.datastructure.Comment;
import de.klosebrothers.specparser.gauge.datastructure.Specification;
import de.klosebrothers.specparser.gauge.datastructure.Step;
import de.klosebrothers.specparser.gauge.datastructure.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static de.klosebrothers.specparser.gauge.SpecBuilder.toSpecification;
import static de.klosebrothers.specparser.gauge.TestEnvironment.*;
import static org.assertj.core.api.Assertions.*;

class SpecParserTest {

    //Positive Tests

    @Test
    void gaugeShouldHaveThisComment() {
        Specification specification = toSpecification(gauge);
        assertThat(specification.getComments())
                .containsExactly(
                        new Comment("The admin user must be able to search for available products on the search page"));
    }

    @Test
    void gaugeShouldParseWithCommentFirst() {
        String comment = "This is a Comment plz parse mee :) \n";
        Specification specification = toSpecification(comment + gauge);
        assertThat(specification.getComments())
                .containsExactly(
                    new Comment("The admin user must be able to search for available products on the search page"));
    }

    @Test
    void smallGaugeShouldHaveTagsSearchAdmin() {
        Specification specification = toSpecification(gaugeSmall);
        assertThat(specification.getTags().getTags())
                .containsExactly(
                        new Tag("search"),
                        new Tag("admin"));
    }

    @Test
    void smallGaugeHasOneScenarioNamedSuccessfulSearch() {
        Specification specification = toSpecification(gaugeSmall);
        assertThat(specification.getScenarios().get(0).getHeading())
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

        assertThat(contextSteps
        ).containsExactly(
                new Step("Sign up for user \"mike\""),
                new Step("Log in as \"mike\""));

        assertThat(tearDownSteps)
                .containsExactly(
                        new Step("Logout user \"mike\""),
                        new Step("Delete user \"mike\""));

    }

    // TODO: 13.12.19 Replace Excetions w/ GaugeExeptions
    //Negative Tests
    @Test
    void gaugeWithoutHeading() {
        String search_specification = deleteLineWith(gauge, "Search specification");
        assertThatExceptionOfType(ClassCastException.class)
                .isThrownBy(() -> toSpecification(search_specification));
    }

    @Test
    void smallGaugeWithoutSteps() {
        String gaugeWithoutSteps = deleteLineWith(gaugeSmall, "User must be logged in as", "\"Cup Cakes\" should show up in the");
        assertThatNullPointerException()
                .isThrownBy(() -> toSpecification(gaugeWithoutSteps));
    }

}
