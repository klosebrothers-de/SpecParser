package de.klosebrothers.specparser.gauge;

import java.util.Arrays;

public class TestEnvironment {

    public static final String gauge =
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

    public static final String gaugeSmall =
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

    public static final String gaugeWithTearDown = "# Delete project\n" +
            "\n" +
            "* Sign up for user \"mike\"\n" +
            "* Log in as \"mike\"\n" +
            "\n" +
            "## Delete single project\n" +
            "\n" +
            "* Delete the \"example\" project\n" +
            "* Ensure \"example\" project has been deleted\n" +
            "\n" +
            "## Delete multiple projects\n" +
            "\n" +
            "* Delete all the projects in the list\n" +
            "* Ensure project list is empty\n" +
            "\n" +
            "____________________\n" +
            "These are teardown steps\n" +
            "\n" +
            "* Logout user \"mike\"\n" +
            "* Delete user \"mike\"";

    public static String deleteLineWith(String from, String... what) {
        return Arrays.stream(from.split("\n"))
                .filter(line -> Arrays.stream(what)
                        .noneMatch(line::contains))
                .reduce((t, op) -> t.concat(op + "\n"))
                .get();
    }
}
