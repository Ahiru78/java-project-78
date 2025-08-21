package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema required() {
        addCheck("required", value -> value != null);
        isRequired = true;
        return this;
    }

    public NumberSchema positive() {
        addCheck("positive", value -> value != null && value > 0);
        return this;
    }

    public NumberSchema range(int a, int b) {
        addCheck("range", value -> value != null && value >= a && value <= b);
        return this;
    }
}
