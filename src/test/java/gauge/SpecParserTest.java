package gauge;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class SpecParserTest {

    private static String gauge =
            "# Search specification\n" +
                    "Tags: search, admin\n" +
                    "\n" +
                    "The admin user must be able to search for available products on the search page\n" +
                    "\n" +
                    "## Successful search\n" +
                    "Tags: successful\n" +
                    "\n" +
                    "For an existing product name, the search result will contain the product name\n" +
                    "\n" +
                    "* User must be logged in as \"admin\"\n" +
                    "* Open the product search page\n" +
                    "* Search for product \"Cup Cakes\"\n" +
                    "* \"Cup Cakes\" should show up in the search results\n" +
                    "\n" +
                    "\n" +
                    "## Unsuccessfull search\n" +
                    "\n" +
                    "On an unknown product name search the search results will be empty\n" +
                    "\n" +
                    "* User must be logged in as \"admin\"\n" +
                    "* Open the product search page\n" +
                    "* Search for product \"unknown\"\n" +
                    "* The search results will be empty";

    private static String gaugeSmall =
            "# Search specification\n" +
                    "Tags: search, admin\n" +
                    "\n" +
                    "The admin user must be able to search for available products on the search page\n" +
                    "\n" +
                    "## Successful search\n" +
                    "Tags: successful\n" +
                    "\n" +
                    "* User must be logged in as \"admin\"\n" +
                    "* \"Cup Cakes\" should show up in the search results";

    //Positive Tests

    @Test
    void gaugeShouldHaveThisDescription() {
        Specification specification = SpecParser.parse(gauge);
        Assertions.assertEquals("The admin user must be able to search for available products on the search page",
                specification.getDescription());
    }

    @Test
    void smallGaugeShouldHaveTagsSearchAdmin() {
        Specification specification = SpecParser.parse(gaugeSmall);
        Assertions.assertArrayEquals(Arrays.asList(new Tag("search"), new Tag("admin")).toArray(),
                specification.getTags().toArray());
    }

    @Test
    void smallGaugeHasOneScenarioNamedSuccessfulSearch() {
        Specification specification = SpecParser.parse(gaugeSmall);
        Assertions.assertEquals("Successful search",
                specification.getScenarios().get(0).getHeading());
    }

    @Test
    void gaugeHasOneScenarioWith4Steps() {
        Specification specification = SpecParser.parse(gauge);
        Assertions.assertArrayEquals(new Step[]{
                        new Step("User must be logged in as \"admin\""),
                        new Step("Open the product search page"),
                        new Step("Search for product \"Cup Cakes\""),
                        new Step("\"Cup Cakes\" should show up in the search results"),},
                specification.getScenarios().get(0).getSteps().toArray());
    }

    @Test
    void smallGaugeHasOneScenarioWith2Steps() {
        Specification specification = SpecParser.parse(gaugeSmall);
        Assertions.assertArrayEquals(new Step[]{
                        new Step("User must be logged in as \"admin\""),
                        new Step("\"Cup Cakes\" should show up in the search results"),},
                specification.getScenarios().get(0).getSteps().toArray());
    }

    @Test
    void gaugeHas2Scenarios() {
        Specification specification = SpecParser.parse(gauge);
        Assertions.assertEquals(2,
                specification.getScenarios().size());
    }

    // TODO: 13.12.19 Replace Excetions w/ GaugeExeptions
    //Negative Tests
    @Test
    void gaugeWithoutHeading() {
        String search_specification = deleteLineWith(gauge, "Search specification");
        Assertions.assertThrows(ClassCastException.class, () -> SpecParser.parse(search_specification));
    }

    @Test
    void smallGaugeWithoutSteps() {
        String gaugeWithoutSteps = deleteLineWith(gaugeSmall, "User must be logged in as", "\"Cup Cakes\" should show up in the");
        Assertions.assertThrows(NullPointerException.class, () -> SpecParser.parse(gaugeWithoutSteps));
    }

    private String deleteLineWith(String from, String... what) {
        return Arrays.stream(from.split("\n"))
                .filter(line -> Arrays.stream(what)
                        .noneMatch(line::contains))
                .reduce((t, op) -> t.concat(op + "\n"))
                .get();
    }
}