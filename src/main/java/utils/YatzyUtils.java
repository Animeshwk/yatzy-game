package utils;

public class YatzyUtils {

    public static int[] counts(int... dice) {
        int[] counts = new int[6];

        for (int die : dice) {
            counts[die - 1]++;
        }
        return counts;
    }
}
