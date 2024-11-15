package hexlet.code;

import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.BaseSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapSchemaTest {

    @Test
    void testRequiredWithNull() {
        Validator v = new Validator();
        MapSchema schema = v.map();

        // Проверка для пустой мапы и null
        assertTrue(schema.isValid(null));

        // Проверка для пустой карты
        assertTrue(schema.isValid(new HashMap<>()));  // пустая карта тоже валидна

        // Проверка после применения required
        schema.required();
        assertFalse(schema.isValid(null));  // null невалиден после required
        assertTrue(schema.isValid(new HashMap<>()));  // Пустая карта валидна, т.к. она не null
    }

    @Test
    void testShapeValidation() {
        Validator v = new Validator();
        MapSchema schema = v.map();

        // Схема с обязательными полями
        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", v.string().required());
        schemas.put("lastName", v.string().required().minLength(2));

        schema.shape(schemas);

        // Тестирование с валидными данными
        Map<String, String> validMap = new HashMap<>();
        validMap.put("firstName", "John");
        validMap.put("lastName", "Smith");
        assertTrue(schema.isValid(validMap));  // Ожидаем, что данные будут валидны

        // Тестирование с отсутствующим ключом (lastName)
        Map<String, String> invalidMap1 = new HashMap<>();
        invalidMap1.put("firstName", "John");
        assertFalse(schema.isValid(invalidMap1));  // Ожидаем, что данные не будут валидны, т.к. нет lastName

        // Тестирование с неверным значением (too short lastName)
        Map<String, String> invalidMap2 = new HashMap<>();
        invalidMap2.put("firstName", "John");
        invalidMap2.put("lastName", "A");
        assertFalse(schema.isValid(invalidMap2));  // Ожидаем, что данные не будут валидны
    }

}

