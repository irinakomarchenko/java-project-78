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
        assertTrue(schema.isValid("hexlet"));


        assertTrue(schema.contains("wh").isValid("what does the fox say"));
        assertTrue(schema.contains("what").isValid("what does the fox say"));
        assertFalse(schema.contains("whatthe").isValid("what does the fox say"));


        assertFalse(schema.isValid("what does the fox say"));


        StringSchema schema1 = v.string();
        schema1.minLength(10).minLength(4);


        assertTrue(schema1.isValid("Hexlet"));
    }
}
