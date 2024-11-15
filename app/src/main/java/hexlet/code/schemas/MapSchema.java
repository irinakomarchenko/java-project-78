package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public final class MapSchema extends BaseSchema<Map<?, ?>> {
    private Map<String, Predicate<Map<?, ?>>> checkList = new HashMap<>();

    /**
     * Marks the Map as required.
     * @return the current MapSchema instance for chaining.
     */
    @Override
    public MapSchema required() {
        super.required();
        addCondition("map", value -> value instanceof Map);
        return this;
    }

    /**
     * Validates the size of the map.
     * @param size the expected size of the map.
     * @return the current MapSchema instance for chaining.
     */
    public MapSchema sizeof(int size) {
        addCondition("size", value -> value != null && value.size() == size);
        return this;
    }

    /**
     * Validates that the map conforms to the specified schema for each key.
     * @param schemas a map of keys and their associated schemas.
     * @param <T> the type of values expected for each key.
     * @return the current MapSchema instance for chaining.
     */
    public <T> MapSchema shape(Map<String, BaseSchema<T>> schemas) {
        checkList.put("shape", map -> {
            if (map == null) {
                return false; // Map is null, invalid
            }

            // Check if all keys have valid values according to the schema
            for (var entry : schemas.entrySet()) {
                String key = entry.getKey();
                BaseSchema<T> schema = entry.getValue();

                // Check if the map contains the key and it has a valid value
                if (!map.containsKey(key) || map.get(key) == null) {
                    return false; // Invalid map for key
                }

                T value = (T) map.get(key);
                if (!schema.isValid(value)) {
                    return false; // Invalid value for key
                }
            }
            return true;
        });

        return this;
    }

    @Override
    public boolean isValid(Map<?, ?> value) {
        if (!super.isValid(value)) {
            return false;
        }
        // Apply the shape checks
        for (Predicate<Map<?, ?>> check : checkList.values()) {
            if (!check.test(value)) {
                return false;
            }
        }
        return true;
    }
}

