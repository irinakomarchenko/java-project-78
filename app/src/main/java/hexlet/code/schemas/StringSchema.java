package hexlet.code.schemas;

public class StringSchema {
    private boolean isRequired = false;
    private String containsSubstring = null;
    private int minLength = -1;

    public boolean isValid(String value) {
        if (value == null || value.isEmpty()) {
            return !isRequired;
        }

        if (containsSubstring != null && !value.contains(containsSubstring)) {
            return false;
        }

        if (minLength > 0 && value.length() < minLength) {
            return false;
        }

        return true;
    }

    public StringSchema required() {
        this.isRequired = true;
        return this;
    }

    public StringSchema contains(String substring) {
        this.containsSubstring = substring;
        return this;
    }

    public StringSchema minLength(int length) {
        this.minLength = length;
        return this;
    }

}

