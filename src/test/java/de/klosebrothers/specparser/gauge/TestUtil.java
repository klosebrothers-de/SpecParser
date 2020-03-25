package de.klosebrothers.specparser.gauge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestUtil {

    public static String removeEmptyLines(String expected) {
        return Arrays.stream(expected.split("\n"))
                .filter(s -> !s.trim().isEmpty())
                .collect(Collectors.joining("\n"));
    }

    public static String deleteLineWith(String from, String... what) {
        return Arrays.stream(from.split("\n"))
                .filter(line -> Arrays.stream(what)
                        .noneMatch(line::contains))
                .reduce((t, op) -> t.concat(op + "\n"))
                .orElse("");
    }

    public static String addLineAt(String from, int at, String what) {
        List<String> strings = new ArrayList<>(Arrays.asList(from.split("\n")));
        strings.add(at, what);
        return String.join("\n", strings);
    }
}
