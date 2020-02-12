package de.klosebrothers.specparser.gauge;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class EndToEndTest {

    @Test
    @Disabled
    void converting_string_to_specification_and_back_again_should_result_in_the_original_string() {
        String expected = removeEmptyLines(TestEnvironment.gauge);

        String actual = SpecBuilder.fromSpecification(SpecBuilder.toSpecification(expected));

        assertThat(removeEmptyLines(actual)).isEqualTo(expected);
    }

    private String removeEmptyLines(String expected) {
        return Arrays.stream(expected.split("\n"))
                .filter(s -> !s.trim().isEmpty())
                .collect(Collectors.joining("\n"));
    }
}
