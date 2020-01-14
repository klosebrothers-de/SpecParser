package de.klosebrothers.specparser.gauge;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static de.klosebrothers.specparser.gauge.SpecParser.toSpecification;
import static de.klosebrothers.specparser.gauge.TestEnvironment.*;
import static org.junit.jupiter.api.Assertions.*;


public class SpecificationTest {

    // TODO: 14.01.20 testPräfix RAUS
    @Test
    public void testMdFormattedGauge() {
        verifyFromGaugeToSpecAndBack(gauge);
    }

    @Test
    public void testMdFormattedSmallGauge() {
        verifyFromGaugeToSpecAndBack(gaugeSmall);
    }

    @Test
    public void testMdFormattedGaugeWithTearDownAndContextSteps() {
        verifyFromGaugeToSpecAndBack(gaugeWithTearDown);
    }

    private void verifyFromGaugeToSpecAndBack(String gauge) {
        Specification specification = toSpecification(gauge);
        String mdFormatted = SpecBuilder.fromSpecification(specification);
        assertEquals(toSpecification(mdFormatted), specification);
        //assertJ
    }
}
