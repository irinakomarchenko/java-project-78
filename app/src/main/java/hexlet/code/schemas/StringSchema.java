package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {

    public StringSchema required() {
        super.required();
        addCondition("string", value -> value instanceof String && !((String) value).isEmpty());
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
}

