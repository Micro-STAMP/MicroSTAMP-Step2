package microstamp.step2.dto;

import lombok.Data;
import microstamp.step2.data.ConnectionType;
import microstamp.step2.data.Style;

import java.util.List;

@Data
public class ConnectionDto {

    private Long id;
    private ConnectionType connectionType;
    private Long sourceId;
    private Long targetId;
    private Style style;
    private Long controlStructureId;
    private List<String> labelsIds;

}
