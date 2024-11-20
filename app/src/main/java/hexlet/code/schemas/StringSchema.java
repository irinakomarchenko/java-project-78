package hexlet.code.schemas;

import java.util.function.Predicate;

public final class StringSchema extends BaseSchema<String> {

    /**
     * Marks the schema as required.
     * Ensures the string is not null or empty.
     *
     * @return The current StringSchema instance for chaining.
     */
    @Override
    public StringSchema required() {
        Predicate<String> requiredCheck = value -> value != null && !value.isEmpty();
        addCheck("required", requiredCheck);

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

        Predicate<String> lengthCheck = value -> value != null && value.length() >= minLength;

        addCheck("minLength_" + minLength, lengthCheck);

        return this;
    }

    /**
     * Validates that the string contains the specified substring.
     *
     * @param substr The substring that the string must contain.
     * @return The current StringSchema instance for chaining.
     * @throws IllegalArgumentException If the substring is null.
     */
    public StringSchema contains(String substr) {
        if (substr == null) {
            throw new IllegalArgumentException("Substring cannot be null");
        }

        Predicate<String> containsCheck = value -> value != null && value.contains(substr);
        addCheck("contains" + substr, containsCheck);
        return this;
    }
}
