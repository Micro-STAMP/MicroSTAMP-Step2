package microstamp.step2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ControlStructureDto {

    @NotBlank
    private String name;

    @NotNull
    private Long userId;

}
