package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * Abstract class that provides basic validation logic for schemas.
 * This class can be extended to implement specific validation rules for different types.
 * @param <T> The type of the value to be validated.
 */
public abstract class BaseSchema<T> {
    protected Map<String, Predicate<T>> conditions = new HashMap<>();
    protected List<Predicate<T>> additionalChecks = new ArrayList<>();
    protected boolean isRequired = false;

    /**
     * Marks the schema as required.
     * @return The current schema instance for method chaining.
     */
    public BaseSchema<T> required() {
        this.isRequired = true;
        addCondition("required", value -> value != null);
        return this;
    }

    /**
     * Validates the provided value.
     * @param value The value to validate.
     * @return True if the value passes all conditions, otherwise false.
     */
    public boolean isValid(T value) {
        if (value == null) {
            return !isRequired;
        }

        // Check basic conditions
        if (!conditions.values().stream().allMatch(condition -> condition.test(value))) {
            return false;
        }

        // Check additional schema-specific conditions
        return additionalChecks.stream().allMatch(check -> check.test(value));
    }

    /**
     * Adds a new condition for validation.
     * @param name The name of the condition.
     * @param condition The condition to be added.
     */
    protected void addCondition(String name, Predicate<T> condition) {
        conditions.put(name, condition);
    }

    /**
     * Adds a schema-specific validation check.
     * @param check The additional validation check.
     */
    protected void addAdditionalCheck(Predicate<T> check) {
        additionalChecks.add(check);
    }
}
