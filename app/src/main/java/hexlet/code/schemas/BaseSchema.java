package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Abstract class that provides basic validation logic for schemas.
 * @param <T> The type of the value to be validated.
 */
public abstract class BaseSchema<T> {
    // List to store conditions in the order they were added
    protected List<Predicate<T>> conditions = new ArrayList<>();

    // Flag for required validation
    protected boolean isRequired = false;

    /**
     * Marks the schema as required.
     * @return The current schema instance for method chaining.
     */
    public BaseSchema<T> required() {
        this.isRequired = true;
        // Add a condition to ensure the value is not null if required
        addCondition(Objects::nonNull);
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
            return !this.isRequired;
        }

        for (Predicate<T> condition : conditions) {
            if (!condition.test(value)) {
                return false;
            }
        }
        return true;
    }
}
