package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class StringSchemaTest {

    @Test
    void testStringValidator() {
        Validator v = new Validator();
        StringSchema schema = v.string();


        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid(null));


        schema.required();
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertTrue(schema.isValid("what does the fox say"));


        schema.contains("wh");
        assertTrue(schema.isValid("what does the fox say"));
        assertFalse(schema.isValid("hexlet"));


        schema = v.string();
        schema.minLength(4);
        assertTrue(schema.isValid("Hexlet"));


        schema.minLength(10);
        assertFalse(schema.isValid("short"));
        assertTrue(schema.isValid("what does the fox say"));
    }
}

