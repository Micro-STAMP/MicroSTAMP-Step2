package Step2FormTest.models;

import java.util.ArrayList;
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

        List<String> listReturn = new ArrayList<String>();
        listReturn.add(ConnectionType.CONTROL_ACTION.name());
        listReturn.add(ConnectionType.FEEDBACK.name());
        listReturn.add(ConnectionType.COMMUNICATION_CHANNEL.name());

        return listReturn;
    }

    public static List<String> getProcessInputDisturbance(){
        List<String> listReturn = new ArrayList<String>();
        listReturn.add(ConnectionType.PROCESS_INPUT.name());
        listReturn.add(ConnectionType.DISTURBANCE.name());
        return listReturn;
    }

    public static String getProcessOutput(){
        return ConnectionType.PROCESS_OUTPUT.name();
    }

}
