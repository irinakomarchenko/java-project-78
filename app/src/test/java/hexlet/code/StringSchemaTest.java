package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class StringSchemaTest {

    @Test
    void testStringSchemaValidation() {
        // Создание валидатора и схемы
        Validator v = new Validator();
        StringSchema schema = v.string();

        // Проверка до вызова required
        assertTrue(schema.isValid(null)); // null допустим
        assertTrue(schema.isValid("")); // Пустая строка допустима

        // Вызов required - теперь null и пустая строка должны быть недопустимы
        schema.required();
        assertFalse(schema.isValid(null)); // null недопустим
        assertFalse(schema.isValid("")); // Пустая строка недопустима
        assertTrue(schema.isValid("hello")); // Непустая строка допустима

        // Проверка метода contains
        schema.contains("ell");
        assertTrue(schema.isValid("hello")); // Строка содержит "ell"
        assertFalse(schema.isValid("hi")); // Строка не содержит "ell"

        // Проверка метода minLength
        schema.minLength(5);
        assertTrue(schema.isValid("hello world")); // Строка длиной >= 5
        assertFalse(schema.isValid("hi")); // Строка короче 5 символов
    }
}

