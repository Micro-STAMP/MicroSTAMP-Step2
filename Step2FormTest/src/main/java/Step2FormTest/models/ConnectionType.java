package Step2FormTest.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum ConnectionType {

    CONTROL_ACTION,
    FEEDBACK,
    COMUNICATION_CHANNEL;

    public static List<String> carregarAtributos() {
        List<ConnectionType> lista = Arrays.asList(ConnectionType.values());
        List<String> retorno = new ArrayList<String>();
        for (int i = 0; i < lista.size(); i++) {
            retorno.add(lista.get(i).name());
        }
        return retorno;
    }
}
