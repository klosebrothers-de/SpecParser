package de.klosebrothers.specparser.gauge;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static de.klosebrothers.specparser.gauge.TestUtil.removeEmptyLines;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class EndToEndTest {

    @Test
    void converting_string_to_specification_and_back_again_should_result_in_the_original_string() {
        String expected = removeEmptyLines(TestEnvironment.gauge);

        String actual = SpecBuilder.fromSpecification(SpecBuilder.toSpecification(expected));

        assertThat(removeEmptyLines(actual)).isEqualTo(expected);
    }


}
