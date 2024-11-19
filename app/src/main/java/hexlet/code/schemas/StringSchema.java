package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {

    @Override
    public StringSchema required() {
        super.required();
        addCondition(value -> value != null && !value.isEmpty());
        return this;
    }

    public StringSchema contains(String substring) {
        addCondition(value -> value != null && value.contains(substring));
        return this;
    }

    public StringSchema minLength(int length) {
        addCondition(value -> value != null && value.length() >= length);
        return this;
    }
}

