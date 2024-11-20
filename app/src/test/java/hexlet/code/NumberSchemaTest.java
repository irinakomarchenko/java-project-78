package hexlet.code;


import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumberSchemaTest {

    @Test
    void testRangeValidation() {
        Validator v = new Validator();
        NumberSchema schema = v.number();

        assertTrue(schema.isValid(null));

        schema.range(1, 10);
        assertTrue(schema.isValid(5));
        assertFalse(schema.isValid(0));
        assertFalse(schema.isValid(11));

        assertTrue(schema.isValid(1.5));
        assertFalse(schema.isValid(10.5));

        schema.required();
        assertFalse(schema.isValid(null));
    }

}

