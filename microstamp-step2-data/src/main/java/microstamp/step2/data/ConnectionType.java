package microstamp.step2.data;

import java.util.Arrays;
import java.util.List;

public enum ConnectionType {

    CONTROL_ACTION,
    FEEDBACK,
    COMMUNICATION_CHANNEL,
    PROCESS_INPUT,
    PROCESS_OUTPUT,
    DISTURBANCE;

    public static List<ConnectionType> getDefaultTypes() {
        return Arrays.asList(
                CONTROL_ACTION,
                FEEDBACK,
                COMMUNICATION_CHANNEL
        );
    }

    public static List<ConnectionType> getSourceEnvironmentTypes() {
        return Arrays.asList(
                PROCESS_INPUT,
                DISTURBANCE
        );
    }

    public static List<ConnectionType> getTargetEnvironmentTypes() {
        return List.of(PROCESS_OUTPUT);
    }
}
