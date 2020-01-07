package de.klosebrothers.specparser.gauge;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static de.klosebrothers.specparser.gauge.SpecParser.toSpecification;
import static de.klosebrothers.specparser.gauge.TestEnvironment.*;


public class SpecificationTest {

    @Test
    public void testMdFormattedGauge() {
        Specification specification = toSpecification(gauge);
        String mdFormatted = SpecBuilder.fromSpecification(specification);
        Specification expected = toSpecification(mdFormatted);
        Assertions.assertEquals(expected, specification);
    }

    @Test
    public void testMdFormattedSmallGauge() {
        Specification specification = toSpecification(gaugeSmall);
        String mdFormatted = SpecBuilder.fromSpecification(specification);
        Specification expected = toSpecification(mdFormatted);
        Assertions.assertEquals(expected, specification);
    }

    @Test
    public void testMdFormattedGaugeWithTearDownAndContextSteps() {
        Specification specification = toSpecification(gaugeWithTearDown);
        String mdFormatted = SpecBuilder.fromSpecification(specification);
        Specification expected = toSpecification(mdFormatted);
        Assertions.assertEquals(expected, specification);
    }
}
