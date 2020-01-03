package gauge;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static gauge.TestEnvironment.*;

class SpecificationTest {

    @Test
    void mdFormattedGauge() {
        Specification specification = SpecParser.toSpecification(gauge);
        String mdFormatted = SpecBuilder.fromSpecification(specification);
        Specification expected = SpecParser.toSpecification(mdFormatted);
        Assertions.assertEquals(expected, specification);
    }

    @Test
    void mdFormattedSmallGauge() {
        Specification specification = SpecParser.toSpecification(gaugeSmall);
        String mdFormatted = SpecBuilder.fromSpecification(specification);
        Specification expected = SpecParser.toSpecification(mdFormatted);
        Assertions.assertEquals(expected, specification);
    }

    @Test
    void mdFormattedGaugeWithTearDownAndContextSteps() {
        Specification specification = SpecParser.toSpecification(gaugeWithTearDown);
        String mdFormatted = SpecBuilder.fromSpecification(specification);
        Specification expected = SpecParser.toSpecification(mdFormatted);
        Assertions.assertEquals(expected, specification);
    }
}
