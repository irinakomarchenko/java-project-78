package hexlet.code.schemas;

import java.util.Map;

/**
 * Schema for validating map values.
 */
public final class MapSchema extends BaseSchema<Map<?, ?>> {

    /**
     * Marks the schema as required.
     * Ensures the value is a Map and not null.
     *
     * @return The current MapSchema instance for chaining.
     */
    @Override
    public MapSchema required() {
        super.required();
        addCheck(value -> value instanceof Map);
        return this;
    }

    /**
     * Validates the size of the map.
     * Ensures that the map contains exactly the specified number of entries.
     *
     * @param size The expected size of the map.
     * @return The current MapSchema instance for chaining.
     */
    public MapSchema sizeof(int size) {
        addCheck(map -> map.size() == size);
        return this;
    }

    /**
     * Validates that the map conforms to the specified schema for each key.
     *
     * @param schemas A map where each key corresponds to a schema for its value.
     * @param <T> The type of values expected for each key.
     * @return The current MapSchema instance for chaining.
     */
    public <T> MapSchema shape(Map<String, BaseSchema<T>> schemas) {
        addCheck(map -> {

            if (map == null) {
                return false;
            }

            return schemas.entrySet().stream().allMatch(entry -> {
                String key = entry.getKey();
                BaseSchema<T> schema = entry.getValue();
                T value = (T) map.get(key);
                return schema.isValid(value);
            });
        });
        return this;
    }
}
