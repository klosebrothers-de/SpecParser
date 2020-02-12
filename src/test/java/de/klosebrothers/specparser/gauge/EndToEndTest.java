package de.klosebrothers.specparser.gauge;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class EndToEndTest {

    @Test
    @Disabled
    void converting_string_to_specification_and_back_again_should_result_in_the_original_string() {
        String expected = TestEnvironment.gauge;

        String actual = SpecBuilder.fromSpecification(SpecBuilder.toSpecification(expected));

        assertThat(actual).isEqualTo(expected);
    }
}
