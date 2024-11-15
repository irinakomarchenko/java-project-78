package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

/**
 * Abstract class that provides basic validation logic for schemas.
 * This class can be extended to implement specific validation rules for different types.
 * It allows setting conditions like "required" and validating values based on those conditions.
 *
 * @param <T> The type of the value to be validated.
 */
public abstract class BaseSchema<T> {
    protected Map<String, Predicate<T>> conditions = new HashMap<>();
    protected boolean isRequired = false;

    /**
     * Marks the schema as required, meaning the value cannot be null.
     * Adds the "required" condition to the list of validation conditions.
     *
     * @return The current instance of the schema for method chaining.
     */
    public BaseSchema<T> required() {
        this.isRequired = true;
        addCondition("required", value -> value != null);
        return this;
    }

    /**
     * Validates the provided value based on the conditions set in the schema.
     * If the value is null, it will be valid only if the schema is not required.
     *
     * @param value The value to validate.
     * @return True if the value meets all conditions, otherwise false.
     */
    public boolean isValid(T value) {
        if (value == null) {
            return !isRequired;
        }
        return conditions.values().stream().allMatch(condition -> condition.test(value));
    }

    /**
     * Adds a new condition for validation. Conditions are stored in a map with their names.
     *
     * @param name The name of the condition.
     * @param condition The condition to be applied for validation.
     */
    protected void addCondition(String name, Predicate<T> condition) {
        conditions.put(name, condition);
    }
}
