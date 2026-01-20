import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class JavaTestKotlinDemo {

    @ParameterizedTest
    @MethodSource("provide_sum_of_all_dice")
    void javaTest(int actual, int expected) {
        assertThat(actual).isEqualTo(expected);
    }


    static Stream<Arguments> provide_sum_of_all_dice() {
        return Stream.<Arguments>builder()
                .add(arguments(YatzyKotlin.Companion.chance(2, 3, 4, 5, 1), 15))
                .add(arguments(YatzyKotlin.Companion.chance(3, 3, 4, 5, 1), 16))
                .build();
    }

}
