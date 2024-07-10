package microstamp.step2.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StateDto {

    @NotBlank
    private String name;

    private Long variableId;

}
