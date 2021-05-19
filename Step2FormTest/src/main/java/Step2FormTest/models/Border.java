package Step2FormTest.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Border {

    DASHED,
    SOLID,
    ETCHED;

    public static List<String> carregarAtributos() {
        List<Border> lista = Arrays.asList(Border.values());
        List<String> retorno = new ArrayList<String>();
        for (int i = 0; i < lista.size(); i++) {
            retorno.add(lista.get(i).name());
        }
        return retorno;
    }
}
