package microstamp.step2.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LabelDto {

    @NotBlank
    private String label;

    private Long connectionId;

}
