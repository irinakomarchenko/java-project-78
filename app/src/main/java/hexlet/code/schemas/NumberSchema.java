package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema positive() {
        addCondition("positive", value -> value == null || value > 0);
        return this;
    }

    public NumberSchema range(int min, int max) {
        addCondition("range", value -> value >= min && value <= max);
        return this;
    }

    // Переопределяем required() в NumberSchema
    @Override
    public NumberSchema required() {
        super.required(); // Вызов метода required() родительского класса
        addCondition("noNull", value -> value != null); // Дополнительная логика для проверки на null
        return this;
    }
}
