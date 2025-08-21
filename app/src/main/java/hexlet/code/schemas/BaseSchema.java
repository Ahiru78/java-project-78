package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class BaseSchema<T> {

    private Map<String, Predicate<T>> checkList = new HashMap<>();
    protected boolean isRequired = false;

    public final void addCheck(String key, Predicate<T> predicate) {
        checkList.put(key, predicate);
    }

    public final Boolean isValid(T obj) {
        if (obj == null && !isRequired) {
            return true;
        }
        for (var entry : checkList.entrySet()) {
            var condit = entry.getValue();
            if (!condit.test(obj)) {
                return false;
            }
        }
        return  true;
    }
}
