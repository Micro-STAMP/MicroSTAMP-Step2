package microstamp.step2.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Style {

    DASHED,
    SOLID,
    ETCHED;

    public static List<String> loadStyles() {
        List<Style> list = Arrays.asList(Style.values());
        List<String> listReturn = new ArrayList<String>();

        for(Style s : list)
            listReturn.add(s.name());

        return listReturn;
    }
}
