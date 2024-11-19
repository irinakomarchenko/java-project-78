package hexlet.code.schemas;


/**
 * String schema class for validating string-based values.
 */
public final class StringSchema extends BaseSchema<String> {

    @Override
    public StringSchema required() {
        super.required();
        // Add a condition to ensure the string is not empty
        addCondition(value -> value != null && !value.isEmpty());
        return this;
    }

    /**
     * Adds a condition that the string should contain a specific substring.
     * @param substring The substring that should be contained in the string.
     * @return The current schema instance for method chaining.
     */
    public StringSchema contains(String substring) {
        addCondition(value -> value != null && value.contains(substring));
        return this;
    }

    /**
     * Adds a condition that the string should have a minimum length.
     * @param length The minimum length of the string.
     * @return The current schema instance for method chaining.
     */
    public StringSchema minLength(int length) {
        addCondition(value -> value != null && value.length() >= length);
        return this;
    }
}
