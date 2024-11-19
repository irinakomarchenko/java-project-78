package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class StringSchemaTest {

    @Test
    void testRequiredCondition() {
        Validator v = new Validator();
        StringSchema schema = v.string();


        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(""));


        schema.required();
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertTrue(schema.isValid("valid"));
    }

    @Test
    void testContainsCondition() {
        Validator v = new Validator();
        StringSchema schema = v.string();

        schema.required().contains("test");
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid("example"));
        assertTrue(schema.isValid("this is a test"));
    }

    @Test
    void testMinLengthCondition() {
        Validator v = new Validator();
        StringSchema schema = v.string();

        schema.required().minLength(5);
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid("1234"));
        assertTrue(schema.isValid("12345"));
        assertTrue(schema.isValid("1234567"));
    }

    @Test
    void testAllConditions() {
        Validator v = new Validator();
        StringSchema schema = v.string();

        schema.required().contains("hello").minLength(5);
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid("hi"));
        assertFalse(schema.isValid("world!"));
        assertTrue(schema.isValid("hello there"));
        assertTrue(schema.isValid("hello world!"));
    }
}
