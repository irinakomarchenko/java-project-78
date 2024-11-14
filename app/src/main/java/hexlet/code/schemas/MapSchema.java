package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class MapSchema extends BaseSchema<Map<String, Object>> {
    private Integer expectedSize = null;
    private Map<String, BaseSchema<String>> schemas = new HashMap<>();

    @Override
    public MapSchema required() {
        super.required();
        addCondition("typeCheck", Objects::nonNull);
        return this;
    }

    public MapSchema sizeof(int size) {
        this.expectedSize = size;
        addCondition("sizeCheck", value -> value != null && ((Map<?, ?>) value).size() == expectedSize);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<String>> schemaMap) {
        this.schemas = schemaMap;
        addCondition("shapeCheck", value -> {
            if (value == null) {
                return false;
            }
            Map<?, ?> mapValue = (Map<?, ?>) value;
            for (var entry : schemas.entrySet()) {
                String key = entry.getKey();
                BaseSchema<?> schema = entry.getValue();

                // Проверка, что ключ существует, и значение проходит валидацию
                if (mapValue.containsKey(key)) {
                    Object fieldValue = mapValue.get(key);
                    if (!schema.isValid(fieldValue)) {
                        return false;
                    }
                }
            }
            return true;
        });
        return this;
    }

    @Override
    protected Class<Map<String, Object>> getType() {
        return (Class<Map<String, Object>>) (Class<?>) Map.class;
    }
}
