package hexlet.code.schemas;

import java.util.Map;

public class MapSchema<A, B> extends BaseSchema<B> {

    public MapSchema<A, B> required() {
        addCheck("required", value -> value instanceof Map<?, ?>);
        isRequired = true;
        return this;
    }

    public MapSchema<A, B> sizeof(int num) {
        addCheck("sizeof", value -> ((Map<?, ?>) value).size() >= num);
        return this;
    }

    public MapSchema<A, B> shape(Map<A, BaseSchema<B>> schemas) {
        for (var entry : schemas.entrySet()) {
            var key = entry.getKey();
            var val = entry.getValue();
            addCheck("shape", value -> val.isValid(((Map<A, B>) value).get(key)));
        }
        return this;
    }
}
