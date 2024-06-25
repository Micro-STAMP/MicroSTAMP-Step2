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

    public static List<String> loadConnectionTypes() {
        return Arrays.asList(
                CONTROL_ACTION.name(),
                FEEDBACK.name(),
                COMMUNICATION_CHANNEL.name()
        );
    }

    public static List<String> getProcessInputDisturbance() {
        return Arrays.asList(
                PROCESS_INPUT.name(),
                DISTURBANCE.name()
        );
    }

    public static String getProcessOutput() {
        return PROCESS_OUTPUT.name();
    }
}
