import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class YatzyKotlinTest {


    @ParameterizedTest
    @MethodSource("provide_sum_of_all_dice")
    fun chance_scores_sum_of_all_dice(actual: Int, expected: Int) {
        assertThat(actual).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        fun provide_sum_of_all_dice(): Stream<Arguments> {
            return Stream.builder<Arguments>()
                .add(arguments(YatzyKotlin.chance(2, 3, 4, 5, 1), 15))
                .add(arguments(YatzyKotlin.chance(3, 3, 4, 5, 1), 16))
                .build()
        }
    }

    @Test
    fun yatzy_scores_50() {
        assertThat(YatzyKotlin.yatzy(4, 4, 4, 4, 4)).isEqualTo(50)
        assertThat(YatzyKotlin.yatzy(6, 6, 6, 6, 6)).isEqualTo(50)
        assertThat(YatzyKotlin.yatzy(6, 6, 6, 6, 3)).isEqualTo(0)
    }

    @Test
    fun test_1s() {
        assertThat(YatzyKotlin.ones(1, 2, 3, 4, 5)).isEqualTo(1)
        assertThat(YatzyKotlin.ones(1, 2, 1, 4, 5)).isEqualTo(2)
        assertThat(YatzyKotlin.ones(6, 2, 2, 4, 5)).isEqualTo(0)
        assertThat(YatzyKotlin.ones(1, 2, 1, 1, 1)).isEqualTo(4)
    }

    @Test
    fun test_2s() {
        assertThat(YatzyKotlin.twos(1, 2, 3, 2, 6)).isEqualTo(4)
        assertThat(YatzyKotlin.twos(2, 2, 2, 2, 2)).isEqualTo(10)
    }

    @Test
    fun test_threes() {
        assertThat(YatzyKotlin.threes(1, 2, 3, 2, 3)).isEqualTo(6)
        assertThat(YatzyKotlin.threes(2, 3, 3, 3, 3)).isEqualTo(12)
    }

    @Test
    fun fours_test() {
        assertThat(YatzyKotlin(4, 4, 4, 5, 5).fours()).isEqualTo(12)
        assertThat(YatzyKotlin(4, 4, 5, 5, 5).fours()).isEqualTo(8)
        assertThat(YatzyKotlin(4, 5, 5, 5, 5).fours()).isEqualTo(4)
    }

    @Test
    fun fives() {
        assertThat(YatzyKotlin(4, 4, 4, 5, 5).fives()).isEqualTo(10)
        assertThat(YatzyKotlin(4, 4, 5, 5, 5).fives()).isEqualTo(15)
        assertThat(YatzyKotlin(4, 5, 5, 5, 5).fives()).isEqualTo(20)
    }

    @Test
    fun sixes_test() {
        assertThat(YatzyKotlin(4, 4, 4, 5, 5).sixes()).isEqualTo(0)
        assertThat(YatzyKotlin(4, 4, 6, 5, 5).sixes()).isEqualTo(6)
        assertThat(YatzyKotlin(6, 5, 6, 6, 5).sixes()).isEqualTo(18)
    }

    @Test
    fun one_pair() {
        assertThat(YatzyKotlin.score_pair(3, 4, 3, 5, 6)).isEqualTo(6)
        assertThat(YatzyKotlin.score_pair(5, 3, 3, 3, 5)).isEqualTo(10)
        assertThat(YatzyKotlin.score_pair(5, 3, 6, 6, 5)).isEqualTo(12)
    }

    @Test
    fun two_Pair() {
        assertThat(YatzyKotlin.two_pair(3, 3, 5, 4, 5)).isEqualTo(16)
        assertThat(YatzyKotlin.two_pair(3, 3, 5, 5, 5)).isEqualTo(16)
    }

    @Test
    fun three_of_a_kind() {
        assertThat(YatzyKotlin.three_of_a_kind(3, 3, 3, 4, 5)).isEqualTo(9)
        assertThat(YatzyKotlin.three_of_a_kind(5, 3, 5, 4, 5)).isEqualTo(15)
        assertThat(YatzyKotlin.three_of_a_kind(3, 3, 3, 3, 5)).isEqualTo(9)
    }

    @Test
    fun four_of_a_knd() {
        assertThat(YatzyKotlin.four_of_a_kind(3, 3, 3, 3, 5)).isEqualTo(12)
        assertThat(YatzyKotlin.four_of_a_kind(5, 5, 5, 4, 5)).isEqualTo(20)
        assertThat(YatzyKotlin.four_of_a_kind(3, 3, 3, 3, 3)).isEqualTo(12)
    }

    @Test
    fun smallStraight() {
        assertThat(YatzyKotlin.smallStraight(1, 2, 3, 4, 5)).isEqualTo(15)
        assertThat(YatzyKotlin.smallStraight(2, 3, 4, 5, 1)).isEqualTo(15)
        assertThat(YatzyKotlin.smallStraight(1, 2, 2, 4, 5)).isEqualTo(0)
    }

    @Test
    fun largeStraight() {
        assertThat(YatzyKotlin.largeStraight(6, 2, 3, 4, 5)).isEqualTo(20)
        assertThat(YatzyKotlin.largeStraight(2, 3, 4, 5, 6)).isEqualTo(20)
        assertThat(YatzyKotlin.largeStraight(1, 2, 2, 4, 5)).isEqualTo(0)
    }

    @Test
    fun fullHouse() {
        assertThat(YatzyKotlin.fullHouse(6, 2, 2, 2, 6)).isEqualTo(18)
        assertThat(YatzyKotlin.fullHouse(2, 3, 4, 5, 6)).isEqualTo(0)
    }
}