package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class StringSchemaTest {
    @Test
    void testIsValidWithNonEmptyString() {
        StringSchema schema = new StringSchema();

        assertTrue(schema.isValid("hello"));
        assertTrue(schema.isValid("world"));

        schema.required();
        assertTrue(schema.isValid("hello"));
        assertFalse(schema.isValid(""));
    }

    @Test
    void testIsValidWithNullValue() {
        StringSchema schema = new StringSchema();

        assertTrue(schema.isValid(null));

        schema.required();
        assertFalse(schema.isValid(null));
    }
}

