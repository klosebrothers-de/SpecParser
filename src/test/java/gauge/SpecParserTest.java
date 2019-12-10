package gauge;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SpecParserTest {

    static String gauge =
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

    @Test
    void parse() {
        Specification specification = SpecParser.parse(gauge);
        Assertions.assertEquals(specification.getDescription(),
                "The admin user must be able to search for available products on the search page");
    }
}