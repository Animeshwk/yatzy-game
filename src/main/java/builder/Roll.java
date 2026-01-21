package builder;


/**
 * Immutable value object representing the outcome of a single scoring action/turn.
 * <p>
 * A {@code Roll} captures:
 * <ul>
 *   <li>the user-facing message describing the chosen scoring category</li>
 *   <li>the numeric score computed for the roll</li>
 *   <li>the user-facing message describing what category result was achieved</li>
 * </ul>
 *
 * <p>
 * Instances are immutable: all fields are {@code final} and can only be set via the {@link Builder}.
 * This makes {@code Roll} safe to share between components without defensive copying.
 *
 * <h2>Construction</h2>
 * <p>
 * Create instances using {@link #builder()}:
 * <pre>{@code
 * Roll roll = Roll.builder()
 *     .chosenCategory("You've chosen CHANCE as score category")
 *     .score(17)
 *     .categoryResult("You've got CHANCE")
 *     .build();
 * }</pre>
 *
 * <h2>String representation</h2>
 * <p>
 * {@link #toString()} produces a multi-line, user-friendly format:
 * <pre>
 * ROLL
 * &lt;chosenCategory&gt;
 * &lt;score&gt;
 * &lt;categoryResult&gt;
 * </pre>
 * This is particularly useful when output strategies print a {@code Roll} directly
 * (e.g., {@code System.out.println(roll)}).
 */

public final class Roll {
    private final String chosenCategory;
    private final int score;
    private final String categoryResult;


    /**
     * Constructs a {@code Roll} from the provided {@link Builder}.
     * <p>
     * This constructor is intentionally private to enforce use of the builder.
     *
     * @param builder the builder containing values to copy into this immutable instance
     */

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


    /**
     * Creates a new {@link Builder} for constructing {@code Roll} instances.
     *
     * @return a new builder
     */

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "ROLL" +
                "\n" + chosenCategory +
                "\n" + score +
                "\n" + categoryResult;
    }


    /**
     * Builder for creating immutable {@link Roll} instances.
     * <p>
     * In this builder, each setter returns the same builder instance to allow method chaining.
     */

    public static final class Builder {
        private String chosenCategory;
        private int score;
        private String categoryResult;

        /**
         * No-argument constructor.
         */
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


        /**
         * Builds an immutable {@link Roll} instance using the current builder state.
         * <p>
         *
         * @return a new {@link Roll} instance
         */

        public Roll build() {
            return new Roll(this);
        }
    }
}

