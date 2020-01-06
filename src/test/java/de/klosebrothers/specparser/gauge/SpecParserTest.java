package de.klosebrothers.specparser.gauge;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static de.klosebrothers.specparser.gauge.SpecParser.*;
import static org.junit.jupiter.api.Assertions.*;
import static de.klosebrothers.specparser.gauge.TestEnvironment.*;

class SpecParserTest {

    //Positive Tests

    @Test
    void gaugeShouldHaveThisComment() {
        Specification specification = toSpecification(gauge);
        assertEquals("The admin user must be able to search for available products on the search page",
                specification.getComment());
    }

    @Test
    void smallGaugeShouldHaveTagsSearchAdmin() {
        Specification specification = toSpecification(gaugeSmall);
        Assertions.assertArrayEquals(Arrays.asList(new Tag("search"), new Tag("admin")).toArray(),
                specification.getTags().toArray());
    }

    @Test
    void smallGaugeHasOneScenarioNamedSuccessfulSearch() {
        Specification specification = toSpecification(gaugeSmall);
        assertEquals("Successful search",
                specification.getScenarios().get(0).getHeading());
    }

    @Test
    void gaugeHasOneScenarioWith4Steps() {
        Specification specification = toSpecification(gauge);
        Assertions.assertArrayEquals(new Step[]{
                        new Step("User must be logged in as \"admin\""),
                        new Step("Open the product search page"),
                        new Step("Search for product \"Cup Cakes\""),
                        new Step("\"Cup Cakes\" should show up in the search results"),},
                specification.getScenarios().get(0).getSteps().toArray());
    }

    @Test
    void smallGaugeHasOneScenarioWith2Steps() {
        Specification specification = toSpecification(gaugeSmall);
        Assertions.assertArrayEquals(new Step[]{
                        new Step("User must be logged in as \"admin\""),
                        new Step("\"Cup Cakes\" should show up in the search results"),},
                specification.getScenarios().get(0).getSteps().toArray());
    }

    @Test
    void gaugeHas2Scenarios() {
        Specification specification = toSpecification(gauge);
        assertEquals(2,
                specification.getScenarios().size());
    }

    @Test
    void shouldParseGaugeWithTearDownAndContextSteps() {
        Specification specification = toSpecification(gaugeWithTearDown);
        List<Step> tearDownSteps = specification.getTearDownSteps();
        List<Step> contextSteps = specification.getContextSteps();

        assertEquals(2, contextSteps.size());
        assertEquals("Sign up for user \"mike\"", contextSteps.get(0).getStepText());
        assertEquals("Log in as \"mike\"", contextSteps.get(1).getStepText());

        assertEquals(2, tearDownSteps.size());
        assertEquals("Logout user \"mike\"", tearDownSteps.get(0).getStepText());
        assertEquals("Delete user \"mike\"", tearDownSteps.get(1).getStepText());
    }

    // TODO: 13.12.19 Replace Excetions w/ GaugeExeptions
    //Negative Tests
    @Test
    void gaugeWithoutHeading() {
        String search_specification = deleteLineWith(gauge, "Search specification");
        assertThrows(ClassCastException.class, () -> toSpecification(search_specification));
    }

    @Test
    void smallGaugeWithoutSteps() {
        String gaugeWithoutSteps = deleteLineWith(gaugeSmall, "User must be logged in as", "\"Cup Cakes\" should show up in the");
        assertThrows(NullPointerException.class, () -> toSpecification(gaugeWithoutSteps));
    }


}
