package microstamp.step2.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import microstamp.step2.data.ConnectionType;
import microstamp.step2.data.Style;

@Data
public class ConnectionDto {

    @NotNull
    private ConnectionType connectionType;

    @NotNull
    private Long sourceId;

    @NotNull
    private Long targetId;

    private Style style;

    @NotNull
    private Long controlStructureId;

}
