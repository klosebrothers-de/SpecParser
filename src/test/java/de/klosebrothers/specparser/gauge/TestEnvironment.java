package de.klosebrothers.specparser.gauge;


import com.google.common.io.Resources;
import lombok.SneakyThrows;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static com.google.common.io.Resources.getResource;

public class TestEnvironment {

    public static final String gauge = getGauge("gauge.md");
    public static final String gaugeSmall = getGauge("gaugeSmall.md");
    public static final String gaugeWithTearDown = getGauge("gaugeWithTearDown.md");

    @SneakyThrows
    public static String getGauge(String resourceName) {
        return Resources.toString(getResource(resourceName), StandardCharsets.UTF_8);
    }

    public static String deleteLineWith(String from, String... what) {
        return Arrays.stream(from.split("\n"))
                .filter(line -> Arrays.stream(what)
                        .noneMatch(line::contains))
                .reduce((t, op) -> t.concat(op + "\n"))
                .orElse("");
    }
}
