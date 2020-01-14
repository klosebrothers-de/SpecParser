package de.klosebrothers.specparser.gauge;

import org.junit.jupiter.api.Test;

import static de.klosebrothers.specparser.gauge.SpecParser.toSpecification;
import static de.klosebrothers.specparser.gauge.TestEnvironment.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class SpecificationTest {

    @Test
    public void mdFormattedGauge() {
        verifyFromGaugeToSpecAndBack(gauge);
    }

    @Test
    public void mdFormattedSmallGauge() {
        verifyFromGaugeToSpecAndBack(gaugeSmall);
    }

    @Test
    public void mdFormattedGaugeWithTearDownAndContextSteps() {
        verifyFromGaugeToSpecAndBack(gaugeWithTearDown);
    }

    private void verifyFromGaugeToSpecAndBack(String gauge) {
        Specification specification = toSpecification(gauge);
        String mdFormatted = SpecBuilder.fromSpecification(specification);
        assertEquals(toSpecification(mdFormatted), specification);
        //assertJ
    }
}
