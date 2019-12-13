package gauge;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static gauge.TestEnvironment.*;

class SpecificationTest {

    @Test
    void mdFormatted() {
        Specification specification = SpecParser.parse(gauge);
        String mdFormatted = SpecParser.mdFormatted(specification);
        Specification expected = SpecParser.parse(mdFormatted);
        Assertions.assertEquals(expected, specification);
    }
}