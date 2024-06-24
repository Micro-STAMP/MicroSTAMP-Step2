package microstamp.step2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import microstamp.step2.data.Style;

@Data
public abstract class ComponentDto {

    @NotBlank
    private String name;

    private Boolean isVisible;

    private Long fatherId;

    @NotNull
    private Long controlStructureId;

    private Style border;

    @NotBlank
    private String type;
}
