import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class YatzyJavaTest {

    @Test
    public void chance_scores_sum_of_all_dice() {
        int expected = 15;
        int actual = YatzyJava.chance(2, 3, 4, 5, 1);
        assertEquals(expected, actual);
        assertEquals(16, YatzyJava.chance(3, 3, 4, 5, 1));
    }

    @Test
    public void yatzy_scores_50() {
        int expected = 50;
        int actual = YatzyJava.yatzy(4, 4, 4, 4, 4);
        assertEquals(expected, actual);
        assertEquals(50, YatzyJava.yatzy(6, 6, 6, 6, 6));
        assertEquals(0, YatzyJava.yatzy(6, 6, 6, 6, 3));
    }

    @Test
    public void test_1s() {
        assertTrue(YatzyJava.ones(1, 2, 3, 4, 5) == 1);
        assertEquals(2, YatzyJava.ones(1, 2, 1, 4, 5));
        assertEquals(0, YatzyJava.ones(6, 2, 2, 4, 5));
        assertEquals(4, YatzyJava.ones(1, 2, 1, 1, 1));
    }

    @Test
    public void test_2s() {
        assertEquals(4, YatzyJava.twos(1, 2, 3, 2, 6));
        assertEquals(10, YatzyJava.twos(2, 2, 2, 2, 2));
    }

    @Test
    public void test_threes() {
        assertEquals(6, YatzyJava.threes(1, 2, 3, 2, 3));
        assertEquals(12, YatzyJava.threes(2, 3, 3, 3, 3));
    }

    @Test
    public void fours_test() {
        assertEquals(12, new YatzyJava(4, 4, 4, 5, 5).fours());
        assertEquals(8, new YatzyJava(4, 4, 5, 5, 5).fours());
        assertEquals(4, new YatzyJava(4, 5, 5, 5, 5).fours());
    }

    @Test
    public void fives() {
        assertEquals(10, new YatzyJava(4, 4, 4, 5, 5).fives());
        assertEquals(15, new YatzyJava(4, 4, 5, 5, 5).fives());
        assertEquals(20, new YatzyJava(4, 5, 5, 5, 5).fives());
    }

    @Test
    public void sixes_test() {
        assertEquals(0, new YatzyJava(4, 4, 4, 5, 5).sixes());
        assertEquals(6, new YatzyJava(4, 4, 6, 5, 5).sixes());
        assertEquals(18, new YatzyJava(6, 5, 6, 6, 5).sixes());
    }

    @Test
    public void one_pair() {
        assertEquals(6, YatzyJava.score_pair(3, 4, 3, 5, 6));
        assertEquals(10, YatzyJava.score_pair(5, 3, 3, 3, 5));
        assertEquals(12, YatzyJava.score_pair(5, 3, 6, 6, 5));
    }

    @Test
    public void two_Pair() {
        assertEquals(16, YatzyJava.two_pair(3, 3, 5, 4, 5));
        assertEquals(16, YatzyJava.two_pair(3, 3, 5, 5, 5));
    }

    @Test
    public void three_of_a_kind() {
        assertEquals(9, YatzyJava.three_of_a_kind(3, 3, 3, 4, 5));
        assertEquals(15, YatzyJava.three_of_a_kind(5, 3, 5, 4, 5));
        assertEquals(9, YatzyJava.three_of_a_kind(3, 3, 3, 3, 5));
    }

    @Test
    public void four_of_a_knd() {
        assertEquals(12, YatzyJava.four_of_a_kind(3, 3, 3, 3, 5));
        assertEquals(20, YatzyJava.four_of_a_kind(5, 5, 5, 4, 5));
        assertEquals(12, YatzyJava.four_of_a_kind(3, 3, 3, 3, 3));
    }

    @Test
    public void smallStraight() {
        assertEquals(15, YatzyJava.smallStraight(1, 2, 3, 4, 5));
        assertEquals(15, YatzyJava.smallStraight(2, 3, 4, 5, 1));
        assertEquals(0, YatzyJava.smallStraight(1, 2, 2, 4, 5));
    }

    @Test
    public void largeStraight() {
        assertEquals(20, YatzyJava.largeStraight(6, 2, 3, 4, 5));
        assertEquals(20, YatzyJava.largeStraight(2, 3, 4, 5, 6));
        assertEquals(0, YatzyJava.largeStraight(1, 2, 2, 4, 5));
    }

    @Test
    public void fullHouse() {
        assertEquals(18, YatzyJava.fullHouse(6, 2, 2, 2, 6));
        assertEquals(0, YatzyJava.fullHouse(2, 3, 4, 5, 6));
    }
}
