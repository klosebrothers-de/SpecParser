package de.klosebrothers.specparser.gauge;

import org.junit.jupiter.api.Test;

import static de.klosebrothers.specparser.gauge.SpecBuilder.*;
import static de.klosebrothers.specparser.gauge.TestEnvironment.*;
import static org.assertj.core.api.Assertions.assertThat;

class SpecificationTest {

    @Test
    void mdFormattedGauge() {
        verifyFromGaugeToSpecAndBack(gauge);
    }

    @Test
    void mdFormattedSmallGauge() {
        verifyFromGaugeToSpecAndBack(gaugeSmall);
    }

    @Test
    void mdFormattedGaugeWithTearDownAndContextSteps() {
        verifyFromGaugeToSpecAndBack(gaugeWithTearDown);
    }

    private void verifyFromGaugeToSpecAndBack(String gauge) {
        Specification specification = toSpecification(gauge);
        String mdFormatted = fromSpecification(specification);
        assertThat(toSpecification(mdFormatted)).isEqualTo(specification);
        //assertJ
    }
}
