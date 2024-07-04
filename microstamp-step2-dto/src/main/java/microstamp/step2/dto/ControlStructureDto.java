package microstamp.step2.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ControlStructureDto {

    @NotBlank
    private String name;

    private Long userId;

}
