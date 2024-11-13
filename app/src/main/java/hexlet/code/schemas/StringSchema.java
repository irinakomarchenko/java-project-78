package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String> {

    // Переопределяем required() в StringSchema
    @Override
    public StringSchema required() {
        super.required(); // Вызов метода required() родительского класса
        addCondition("notEmpty", value -> value != null && !value.isEmpty()); // Дополнительная логика для пустых строк
        return this;
    }

    public StringSchema contains(String substring) {
        addCondition("contains", value -> value != null && value.contains(substring));
        return this;
    }

    public StringSchema minLength(int length) {
        addCondition("minLength", value -> value != null && value.length() >= length);
        return this;
    }

    // Возвращаем тип String для метода getType()
    @Override
    protected Class<String> getType() {
        return String.class;
    }
}
