package hexlet.code.schemas;

import java.util.function.Predicate;

public final class StringSchema extends BaseSchema<String> {

    private Predicate<String> minLengthCondition = null;

    @Override
    public StringSchema required() {
        super.required();
        addCondition(value -> value != null && !value.isEmpty());
        return this;
    }

    public StringSchema contains(String substring) {
        if (substring == null) {
            throw new IllegalArgumentException("Substring cannot be null");
        }

        addCondition(value -> value != null && value.contains(substring));
        return this;
    }

    public StringSchema minLength(int length) {
        if (length < 0) {
            throw new IllegalArgumentException("Minimum length cannot be negative");
        }

        if (minLengthCondition != null) {
            conditions.remove(minLengthCondition);
        }

        minLengthCondition = value -> value != null && value.length() >= length;
        addCondition(minLengthCondition);

        return this;
    }
}
