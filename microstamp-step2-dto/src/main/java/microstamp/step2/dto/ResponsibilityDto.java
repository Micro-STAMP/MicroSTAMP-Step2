package microstamp.step2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ResponsibilityDto {

    @NotBlank
    private String responsibility;

    @NotNull
    private Long componentId;

    @NotBlank
    private String systemSafetyConstraintAssociated;

}
