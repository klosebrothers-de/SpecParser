package de.klosebrothers.specparser.gauge;

import de.klosebrothers.specparser.gauge.datastructure.Specification;
import org.junit.jupiter.api.Test;

import static de.klosebrothers.specparser.gauge.SpecBuilder.fromSpecification;
import static de.klosebrothers.specparser.gauge.SpecBuilder.toSpecification;
import static de.klosebrothers.specparser.gauge.TestEnvironment.*;
import static org.assertj.core.api.Assertions.assertThat;

class SpecificationTest {

    @Test
    void mdFormattedGauge() {
        verifyFromGaugeToSpecAndBack(gauge);
    }

    @Test
    void testOfEqualityFromSameGauge() {
        Specification specification1 = toSpecification(gauge);
        Specification specification2 = toSpecification(gauge);
        assertThat(specification1)
                .isEqualTo(specification2);
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
        Specification actual = toSpecification(mdFormatted);
        assertThat(actual)
                .isEqualTo(specification);
    }
}
