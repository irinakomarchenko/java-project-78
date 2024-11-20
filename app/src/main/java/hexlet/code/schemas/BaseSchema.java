package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Abstract class that provides basic validation logic for schemas.
 *
 * @param <T> The type of the value to be validated.
 */
public abstract class BaseSchema<T> {

    private final Map<String, Predicate<T>> checks = new LinkedHashMap<>();

    /**
     * Marks the schema as required.
     * Ensures that the value being validated cannot be null.
     *
     * @return The current schema instance for chaining.
     */
    public BaseSchema<T> required() {
        addCheck("required", Objects::nonNull);
        return this;
    }

    /**
     * Adds a validation condition to the schema.
     *
     * @param key A unique identifier for the check.
     * @param check A predicate representing the validation logic.
     */
    protected void addCheck(String key, Predicate<T> check) {
        checks.put(key, check);
    }

    /**
     * Validates the value against all added conditions.
     *
     * @param value The value to validate.
     * @return True if the value satisfies all conditions, false otherwise.
     */
    public boolean isValid(T value) {
        return checks.values().stream().allMatch(check -> check.test(value));
    }
}
