package hexlet.code.schemas;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

/**
 * Abstract class that provides basic validation logic for schemas.
 * @param <T> The type of the value to be validated.
 */
public abstract class BaseSchema<T> {
    // Set to store unique conditions
    protected Set<Predicate<T>> conditions = new HashSet<>();

    // List for additional checks that are not part of the main conditions
    protected Set<Predicate<T>> additionalChecks = new HashSet<>();

    // Flag for required validation
    protected boolean isRequired = false;

    /**
     * Marks the schema as required.
     * @return The current schema instance for method chaining.
     */
    public BaseSchema<T> required() {
        this.isRequired = true;
        // Add a "required" condition to ensure the value is not null
        addCondition(value -> value != null);
        return this;
    }

    /**
     * Adds a validation condition.
     * @param condition The condition to add.
     */
    protected void addCondition(Predicate<T> condition) {
        conditions.add(condition);  // Ensure conditions are unique by using a Set
    }

    /**
     * Adds an additional validation check.
     * @param condition The condition to add.
     */
    protected void addAdditionalCheck(Predicate<T> condition) {
        additionalChecks.add(condition);  // Ensure additional checks are unique by using a Set
    }

    /**
     * Validates the value against all conditions and additional checks.
     * @param value The value to validate.
     * @return True if valid, false otherwise.
     */
    public final boolean isValid(T value) {
        // Validate using conditions
        for (Predicate<T> condition : conditions) {
            if (!condition.test(value)) {
                return false;
            }
        }

        // Validate using additional checks
        for (Predicate<T> check : additionalChecks) {
            if (!check.test(value)) {
                return false;
            }
        }

        return true;
    }
}

