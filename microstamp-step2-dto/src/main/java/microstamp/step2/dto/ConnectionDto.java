package microstamp.step2.dto;

import lombok.Data;
import microstamp.step2.data.ConnectionType;
import microstamp.step2.data.Style;

import java.util.List;

@Data
public class ConnectionDto {

    private Long id;
    private ConnectionType connectionType;
    private Long source_id;
    private Long target_id;
    private Style style;
    private Long control_structure_id;
    private List<String> labels_ids;

}
