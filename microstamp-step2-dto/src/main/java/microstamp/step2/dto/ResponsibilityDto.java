package microstamp.step2.dto;

import lombok.Data;

@Data
public class ResponsibilityDto {

    private Long id;

    private String responsibility;

    private Long component_id;

    private String systemSafetyConstraintAssociated;

}
