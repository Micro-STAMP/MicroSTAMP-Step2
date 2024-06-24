package microstamp.step2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LabelDto {

    @NotBlank
    private String label;

    @NotNull
    private Long connectionId;

}
