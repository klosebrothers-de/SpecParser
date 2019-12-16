package gauge;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static gauge.TestEnvironment.*;

class SpecificationTest {

    @Test
    void mdFormattedGauge() {
        Specification specification = SpecParser.parse(gauge);
        String mdFormatted = SpecParser.mdFormatted(specification);
        Specification expected = SpecParser.parse(mdFormatted);
        Assertions.assertEquals(expected, specification);
    }

    @Test
    void mdFormattedSmallGauge() {
        Specification specification = SpecParser.parse(gaugeSmall);
        String mdFormatted = SpecParser.mdFormatted(specification);
        Specification expected = SpecParser.parse(mdFormatted);
        Assertions.assertEquals(expected, specification);
    }

    @Test
    void mdFormattedGaugeWithTearDownAndContextSteps() {
        Specification specification = SpecParser.parse(gaugeWithTearDown);
        String mdFormatted = SpecParser.mdFormatted(specification);
        Specification expected = SpecParser.parse(mdFormatted);
        Assertions.assertEquals(expected, specification);
    }
}