package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema<Number> {

    public NumberSchema required() {
        super.required();
        addCondition("number", value -> value instanceof Number);
        return this;
    }

    public NumberSchema positive() {
        addCondition("positive", value -> value != null && value.doubleValue() > 0);
        return this;
    }

    public NumberSchema range(int min, int max) {
        addCondition("range", value -> value != null && value.doubleValue() >= min && value.doubleValue() <= max);
        return this;
    }
}
