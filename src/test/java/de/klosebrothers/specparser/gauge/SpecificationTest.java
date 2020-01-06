package de.klosebrothers.specparser.gauge;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static de.klosebrothers.specparser.gauge.SpecParser.toSpecification;
import static de.klosebrothers.specparser.gauge.TestEnvironment.*;

class SpecificationTest {

    @Test
    void mdFormattedGauge() {
        Specification specification = toSpecification(gauge);
        String mdFormatted = SpecBuilder.fromSpecification(specification);
        Specification expected = toSpecification(mdFormatted);
        Assertions.assertEquals(expected, specification);
    }

    @Test
    void mdFormattedSmallGauge() {
        Specification specification = toSpecification(gaugeSmall);
        String mdFormatted = SpecBuilder.fromSpecification(specification);
        Specification expected = toSpecification(mdFormatted);
        Assertions.assertEquals(expected, specification);
    }

    @Test
    void mdFormattedGaugeWithTearDownAndContextSteps() {
        Specification specification = toSpecification(gaugeWithTearDown);
        String mdFormatted = SpecBuilder.fromSpecification(specification);
        Specification expected = toSpecification(mdFormatted);
        Assertions.assertEquals(expected, specification);
    }
}
