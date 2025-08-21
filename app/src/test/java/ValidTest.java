import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import hexlet.code.Validator;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidTest {

    @Test
    public void testStringSchema() {
        final Validator i = new Validator();
        final StringSchema schema = i.string();

        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid(null));

        schema.required();
        assertFalse(schema.isValid(""));
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid("True"));

        schema.minLength(7);
        assertTrue(schema.isValid("I was gaining a clue to an inner understanding"));
        assertFalse(schema.isValid("I was"));

        schema.contains("inner understanding");
        assertTrue(schema.isValid("I was gaining a clue to an inner understanding"));
        assertFalse(schema.isValid("I was"));

        schema.contains("outer understanding");
        assertFalse(schema.isValid("I was gaining a clue to an inner understanding"));
    }

    @Test
    public void testNumberSchema() {
        final Validator i = new Validator();
        final NumberSchema schema = i.number();

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(7));

        schema.required();
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(7));

        schema.positive();
        assertTrue(schema.isValid(68));
        assertFalse(schema.isValid(-46));

        schema.range(15, 72);
        assertTrue(schema.isValid(49));
        assertFalse(schema.isValid(5));

        schema.range(50, 72);
        assertFalse(schema.isValid(49));
    }

    @Test
    public void testMapSchema() {
        final Validator i = new Validator();
        final MapSchema schema = i.map();

        Map<String, String> map = new HashMap<>();

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(map));

        schema.required();
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(map));

        schema.sizeof(1);
        assertFalse(schema.isValid(map));
        map.put("person", "person");
        assertTrue(schema.isValid(map));

        schema.sizeof(2);
        assertFalse(schema.isValid(map));

        Map<String, BaseSchema> schemas = new HashMap<>();
        Map<String, String> human1 = new HashMap<>();
        Map<String, String> human2 = new HashMap<>();
        Map<String, String> human3 = new HashMap<>();
        schemas.put("firstName", i.string().required());
        schemas.put("lastName", i.string().required().minLength(2));
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        human2.put("firstName", "John");
        human2.put("lastName", null);
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");

        schema.shape(schemas);
        assertTrue(schema.isValid(human1));
        assertFalse(schema.isValid(human2));
        assertFalse(schema.isValid(human3));
    }
}
