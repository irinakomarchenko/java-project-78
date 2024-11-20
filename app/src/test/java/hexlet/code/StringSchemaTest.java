package hexlet.code;


import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class StringSchemaTest {

    @Test
    void testContainsValidation() {
        Validator v = new Validator();
        StringSchema schema = (StringSchema) v.string().required();

        assertTrue(schema.contains("wh").isValid("what does the fox say"));
        assertFalse(schema.contains("whatthe").isValid("what does the fox say"));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            schema.contains(null);
        });
        assertTrue(exception.getMessage().contains("Substring cannot be null"));
    }

    @Test
    void testMinLengthValidation() {
        Validator v = new Validator();
        StringSchema schema = v.string();

        schema.minLength(3);
        assertTrue(schema.isValid("abc"));
        assertFalse(schema.isValid("ab"));

        schema.minLength(5);
        assertTrue(schema.isValid("abcdef"));
        assertFalse(schema.isValid("abcd"));
    }
}
