package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    protected Map<String, Predicate<T>> conditions = new HashMap<>();
    protected boolean isRequired = false;

    /**
     * Checks schema.
     * @return a schema.
     */
    public BaseSchema<T> required() {
        this.isRequired = true;
        conditions.put("required", value -> value != null);
        return this;
    }

    /**
     * Checks if a given value is valid.
     * @param value
     * @return true or false.
     */
    public boolean isValid(Object value) {
        if (value == null) {
            return !isRequired; // если значение null, и не требуется — возвращаем true
        }
        if (!getType().isInstance(value)) {
            return false; // Проверка типа значения
        }
        T typedValue = getType().cast(value); // Приведение типа
        return conditions.values().stream().allMatch(condition -> condition.test(typedValue));
    }

    /**
     * Adds a condition to check.
     * @param name
     * @param condition
     */
    protected void addCondition(String name, Predicate<T> condition) {
        conditions.put(name, condition);
    }

    // Метод, возвращающий класс типа для проверки
    protected abstract Class<T> getType();
}
