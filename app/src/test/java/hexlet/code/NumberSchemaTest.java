package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class NumberSchemaTest {
    @Test
    void testNumberSchemaValidation() {
        Validator v = new Validator();
        NumberSchema schema = v.number();

        // Проверка с положительным числом
        schema.positive();

        // Добавляем вывод состояния для отладки
        System.out.println("isValid(10) after positive(): " + schema.isValid(10)); // Ожидается true для 10
        assertTrue(schema.isValid(10)); // Ожидается true для положительного числа

        System.out.println("isValid(-10) after positive(): " + schema.isValid(-10)); // Ожидается false для -10
        assertFalse(schema.isValid(-10)); // Ожидается false для отрицательного числа

        // Проверка диапазона
        schema.range(5, 15);

        // Добавляем вывод состояния для отладки
        System.out.println("isValid(10) after range(5, 15): "
                + schema.isValid(10)); // Ожидается true, так как 10 в пределах диапазона [5, 15]
        assertTrue(schema.isValid(10)); // Ожидается true для числа в диапазоне

        System.out.println("isValid(4) after range(5, 15): "
                + schema.isValid(4)); // Ожидается false, так как 4 не входит в диапазон [5, 15]
        assertFalse(schema.isValid(4)); // Ожидается false для числа вне диапазона

        System.out.println("isValid(16) after range(5, 15): "
                + schema.isValid(16)); // Ожидается false, так как 16 выходит за пределы диапазона
        assertFalse(schema.isValid(16)); // Ожидается false для числа вне диапазона

        // Проверка на обязательность
        schema.required();

        // Добавляем вывод состояния для отладки
        System.out.println("isValid(null) after required(): "
                + schema.isValid(null)); // Ожидается false для null, так как обязательное поле
        assertFalse(schema.isValid(null)); // Ожидается false для null, так как обязательное поле
    }
}


