package utils;

/**
 * Utility class for Yatzy Game Application.
 */
public class YatzyUtils {

    /**
     * Computes the frequency of each die face (1 through 6) in the provided dice values.
     * @param dice the dice values to count (varargs)
     * @return an array of size {@code 6} containing counts for faces {@code 1..6}
     *
     * @throws NullPointerException if {@code dice} is {@code null}
     * @throws ArrayIndexOutOfBoundsException if any die value is not in the range {@code 1..6}
     */
    public static int[] counts(int... dice) {
        int[] counts = new int[6];

        for (int die : dice) {
            counts[die - 1]++;
        }
        return counts;
    }
}
