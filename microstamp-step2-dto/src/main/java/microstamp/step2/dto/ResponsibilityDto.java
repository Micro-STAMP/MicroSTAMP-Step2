package microstamp.step2.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ResponsibilityDto {

    @NotBlank
    private String responsibility;

    private Long componentId;

    @NotBlank
    private String systemSafetyConstraintAssociated;

}
