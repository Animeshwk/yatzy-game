package builder;

public final class Roll {
    private final String chosenCategory;
    private final int score;
    private final String categoryResult;

    private Roll(Builder builder) {
        this.chosenCategory = builder.chosenCategory;
        this.score = builder.score;
        this.categoryResult = builder.categoryResult;
    }

    public String getChosenCategory() {
        return chosenCategory;
    }

    public int getScore() {
        return score;
    }

    public String getCategoryResult() {
        return categoryResult;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Builder toBuilder() {
        return new Builder()
                .chosenCategory(this.chosenCategory)
                .score(this.score)
                .categoryResult(this.categoryResult);
    }

    @Override
    public String toString() {
        return "ROLL" +
                "\n" + chosenCategory +
                "\n" + score +
                "\n" + categoryResult;
    }

    public static final class Builder {
        private String chosenCategory;
        private int score;
        private String categoryResult;

        private Builder() {
        }

        public Builder chosenCategory(String chosenCategory) {
            this.chosenCategory = chosenCategory;
            return this;
        }

        public Builder score(int score) {
            this.score = score;
            return this;
        }

        public Builder categoryResult(String categoryResult) {
            this.categoryResult = categoryResult;
            return this;
        }

        public Roll build() {
            return new Roll(this);
        }
    }
}

