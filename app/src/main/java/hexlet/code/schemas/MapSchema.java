package hexlet.code.schemas;

import java.util.Map;

/**
 * Schema for validating maps.
 */
public final class MapSchema extends BaseSchema<Map<?, ?>> {

    /**
     * Marks the Map as required.
     * @return the current MapSchema instance for chaining.
     */
    public MapSchema required() {
        super.required();
        addCondition(value -> value instanceof Map); // Ensure the value is a Map.
        return this;
    }

    /**
     * Validates the size of the map.
     * @param size the expected size of the map.
     * @return the current MapSchema instance for chaining.
     */
    public MapSchema sizeof(int size) {
        addCondition(map -> map != null && map.size() == size); // Validate map size.
        return this;
    }

    /**
     * Validates that the map conforms to the specified schema for each key.
     * @param schemas A map of keys and their associated schemas.
     * @param <T> The type of values expected for each key.
     * @return the current MapSchema instance for chaining.
     */
    public <T> MapSchema shape(Map<String, BaseSchema<T>> schemas) {
        addCondition(map -> {
            if (map == null) {
                return false;
            }

            return schemas.entrySet().stream().allMatch(entry -> {
                String key = entry.getKey();
                BaseSchema<T> schema = entry.getValue();
                T value = (T) map.get(key);

                return map.containsKey(key) && schema.isValid(value);
            });
        });
        return this;
    }
}
