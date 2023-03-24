package homer.controller.protocol;

import java.util.List;

public class ExampleSerializer implements Serializer {

    private final int value;

    public ExampleSerializer(int value) {
        this.value = value;
    }

    @Override
    public List<String> getAdjustableValues() {
        return List.of(Integer.toString(value));
    }

    @Override
    public List<Boolean> getToggleableValues() {
        return List.of();
    }

    // @Override
    // public SerializedState get() {
    //     return new SerializedState() {

    //         @Override
    //         public List<String> getAdjustableValues() {
    //             return List.of(Integer.toString(value));
    //         }

    //         @Override
    //         public List<Boolean> getToggleableValues() {
    //             return List.of();
    //         }

    //     };
    // }

}
