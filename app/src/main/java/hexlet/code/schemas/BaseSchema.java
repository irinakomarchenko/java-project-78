package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    protected Map<String, Predicate<T>> conditions = new HashMap<>();
    protected boolean isRequired = false;

    // Метод required теперь не абстрактный, а обычный
    public BaseSchema<T> required() {
        this.isRequired = true;
        conditions.put("required", value -> value != null);
        return this;
    }

    public boolean isValid(T value) {
        if (value == null) {
            return !isRequired; // если значение null, и не требуется — возвращаем true
        }
        return conditions.values().stream().allMatch(condition -> condition.test(value));
    }

    protected void addCondition(String name, Predicate<T> condition) {
        conditions.put(name, condition);
    }
}
