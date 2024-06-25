package microstamp.step2.data;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Style {

    DASHED,
    SOLID,
    ETCHED;

    public static List<String> loadStyles() {
        return Arrays.stream(values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }
}
