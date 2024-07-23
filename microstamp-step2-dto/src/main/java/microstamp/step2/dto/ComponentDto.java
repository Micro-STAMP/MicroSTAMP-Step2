package microstamp.step2.dto;

import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import microstamp.step2.data.ComponentType;
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

    private ComponentType type;

    @AssertFalse(message = "Cannot create a Component named 'Environment'. Use the default Environment Component for operations involving the Environment.")
    private boolean isNameEqualsEnvironment() {
        return ("Environment").equals(name);
    }
}
