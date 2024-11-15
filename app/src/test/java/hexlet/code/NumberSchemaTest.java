package hexlet.code;


import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumberSchemaTest {

    @Test
    void testRequiredWithNull() {
        Validator v = new Validator();
        NumberSchema schema = v.number();

        // Проверка для числа
        assertTrue(schema.isValid(5));  // 5 - валидное значение

        // Проверка на null до применения дополнительных условий
        assertTrue(schema.isValid(null));  // null валиден

        // Применение positive()
        schema.positive();

        // Проверка на null после применения positive
        assertTrue(schema.isValid(null));

        // Проверка на положительное число
        assertTrue(schema.isValid(5));  // 5 - валидное число


        assertFalse(schema.isValid(-5));  // -5 не валидное число для positive()


        schema.range(1, 10);

        // Проверка на диапазон после применения range
        assertTrue(schema.isValid(5));  // 5 - валидное число для диапазона
        assertFalse(schema.isValid(15)); // 15 - не валидное число для диапазона
    }
}
