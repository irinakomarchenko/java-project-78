package hexlet.code.schemas;

import java.util.Objects;

/**
 * Schema for validating numeric values.
 */
public final class NumberSchema extends BaseSchema<Number> {

    /**
     * Validates that the number is positive.
     * Null values are considered valid unless required is explicitly set.
     *
     * @return The current NumberSchema instance for chaining.
     */
    public NumberSchema positive() {
        addCheck("positive", value -> value == null || value.doubleValue() > 0);
        return this;
    }

    /**
     * Validates that the number is within the specified range (inclusive).
     *
     * @param min The minimum value (inclusive).
     * @param max The maximum value (inclusive).
     * @return The current NumberSchema instance for chaining.
     */
    public NumberSchema range(int min, int max) {
        addCheck("range-" + min + "-" + max, value -> value != null
                && value.doubleValue() >= min && value.doubleValue() <= max);
        return this;
    }
}
