package microstamp.step2.dto;

import lombok.Data;
import microstamp.step2.data.Style;

@Data
public abstract class ComponentDto {

    private Long id;
    private String name;
    private Boolean isVisible;
    private Long fatherId;
    private Long controlStructureId;
    private Style border;
    private String type;

}
