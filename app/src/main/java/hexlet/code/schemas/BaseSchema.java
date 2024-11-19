package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Abstract class that provides basic validation logic for schemas.
 * @param <T> The type of the value to be validated.
 */
public abstract class BaseSchema<T> {
    private final List<Predicate<T>> conditions = new ArrayList<>();
    private boolean isRequired = false;

    /**
     * Marks the schema as required.
     * @return The current schema instance for method chaining.
     */
    public BaseSchema<T> required() {
        this.isRequired = true;
        addCondition(value -> value != null); // Null values are invalid if required.
        return this;
    }

    /**
     * Adds a validation condition.
     * @param condition The condition to add.
     */
    protected void addCondition(Predicate<T> condition) {
        conditions.add(condition);
    }

    /**
     * Validates the value against all conditions.
     * @param value The value to validate.
     * @return True if valid, false otherwise.
     */
    public final boolean isValid(T value) {
        if (value == null) {
            return !isRequired; // Null is valid only if not required.
        }

        // Check all conditions.
        return conditions.stream().allMatch(condition -> condition.test(value));
    }
}
