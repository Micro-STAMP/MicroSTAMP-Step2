package microstamp.step2.dto;

import lombok.Data;

@Data
public class ResponsibilityDto {

    private Long id;

    private String responsibility;

    private Long componentId;

    private String systemSafetyConstraintAssociated;

}
