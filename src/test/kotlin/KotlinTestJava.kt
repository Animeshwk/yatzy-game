import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class KotlinTestJava {

    @ParameterizedTest
    @MethodSource("provide_sum_of_all_dice")
    fun chance_scores_sum_of_all_dice(actual: Int, expected: Int) {
        Assertions.assertThat(actual).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        fun provide_sum_of_all_dice(): Stream<Arguments> {
            return Stream.builder<Arguments>()
                .add(Arguments.arguments(YatzyJava.chance(2, 3, 4, 5, 1), 15))
                .add(Arguments.arguments(YatzyJava.chance(3, 3, 4, 5, 1), 16))
                .build()
        }
    }
}