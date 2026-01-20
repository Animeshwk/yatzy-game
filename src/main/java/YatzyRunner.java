import constant.Category;
import facade.YatzyGame;

public class YatzyRunner {
    public static void main(String[] args) {

        int[] dice = {2,2,3,3,4};
        Category category = Category.FULL_HOUSE;

        YatzyGame yatzyGame = new YatzyGame();
        yatzyGame.play(dice, category);
    }
}
