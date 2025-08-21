package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String> {

    public final StringSchema required() {
        addCheck("required", value -> value != null && !value.isEmpty());
        isRequired = true;
        return this;
    }

    public final StringSchema minLength(int num) {
        addCheck("minLength", value -> String.valueOf(value).length() >= num);
        return this;
    }

    public final StringSchema contains(String str) {
        addCheck("contains", value -> String.valueOf(value).contains(str));
        return this;
    }
}
