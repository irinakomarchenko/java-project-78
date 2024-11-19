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
    }

    @Test
    void testContains() {
        Validator v = new Validator();
        StringSchema schema = v.string();

        schema.required();
        schema.contains("wh");

        assertTrue(schema.isValid("what does the fox say"));
        assertFalse(schema.isValid("hexlet"));


        assertFalse(schema.isValid(null));
    }

    @Test
    void testMinLength() {
        Validator v = new Validator();
        StringSchema schema = v.string();

        schema.required();
        schema.minLength(4);

        assertTrue(schema.isValid("Hexlet"));
        assertFalse(schema.isValid("cat"));

        schema.minLength(10);
        assertFalse(schema.isValid("short"));
        assertTrue(schema.isValid("what does the fox say"));


        assertFalse(schema.isValid(null));
    }

    @Test
    void testEmptyStringAndNullBehavior() {
        Validator v = new Validator();
        StringSchema schema = v.string();


        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid(null));

        schema.required();
        assertFalse(schema.isValid(""));
        assertFalse(schema.isValid(null));
    }

    @Test
    void testContainsAndMinLengthTogether() {
        Validator v = new Validator();
        StringSchema schema = v.string();

        schema.required();
        schema.contains("fox").minLength(10);

        assertTrue(schema.isValid("The quick brown fox"));
        assertFalse(schema.isValid("fox"));
        assertFalse(schema.isValid("The quick brown"));
    }
}
