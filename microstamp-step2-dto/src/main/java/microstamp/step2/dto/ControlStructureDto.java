package microstamp.step2.dto;

import lombok.Data;
import microstamp.step2.data.Component;
import microstamp.step2.data.Connection;

import java.util.List;

@Data
public class ControlStructureDto {

    private List<Component> componentsIds;

    private List<Connection> connectionsIds;

    private String name;

    private Long userId;

}
