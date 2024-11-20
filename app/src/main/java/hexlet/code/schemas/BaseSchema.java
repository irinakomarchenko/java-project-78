package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Abstract class that provides basic validation logic for schemas.
 *
 * @param <T> The type of the value to be validated.
 */
public abstract class BaseSchema<T> {

    private final List<Predicate<T>> checks = new ArrayList<>();
    private boolean isRequired = false;

    /**
     * Marks the schema as required.
     * Ensures that the value being validated cannot be null.
     *
     * @return The current schema instance for chaining.
     */
    public BaseSchema<T> required() {
        this.isRequired = true;
        addCheck(Objects::nonNull);
        return this;
    }

    /**
     * Adds a validation condition to the schema.
     *
     * @param check A predicate representing the validation logic.
     */
    protected void addCheck(Predicate<T> check) {
        checks.add(check);
    }

    /**
     * Validates the value against all added conditions.
     *
     * @param value The value to validate.
     * @return True if the value satisfies all conditions, false otherwise.
     */
    public boolean isValid(T value) {
        if (value == null) {
            return !isRequired;
        }
        return checks.stream().allMatch(check -> check.test(value));
    }
}
