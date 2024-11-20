package hexlet.code.schemas;

/**
 * Schema for validating string values.
 */
public final class StringSchema extends BaseSchema<String> {

    /**
     * Marks the schema as required.
     * Ensures the string is not null or empty.
     *
     * @return The current StringSchema instance for chaining.
     */
    @Override
    public StringSchema required() {
        super.required();
        addCheck(value -> !value.isEmpty());
        return this;
    }

    /**
     * Validates that the string contains the specified substring.
     *
     * @param substring The substring that the string must contain.
     * @return The current StringSchema instance for chaining.
     * @throws IllegalArgumentException If the substring is null.
     */
    public StringSchema contains(String substring) {
        if (substring == null) {
            throw new IllegalArgumentException("Substring cannot be null");
        }

        addCheck(value -> value.contains(substring));
        return this;
    }

    /**
     * Validates that the string has at least the specified minimum length.
     *
     * @param minLength The minimum length of the string.
     * @return The current StringSchema instance for chaining.
     * @throws IllegalArgumentException If minLength is negative.
     */
    public StringSchema minLength(int minLength) {
        if (minLength < 0) {
            throw new IllegalArgumentException("Minimum length cannot be negative");
        }

        addCheck(value -> value.length() >= minLength);
        return this;
    }
}

