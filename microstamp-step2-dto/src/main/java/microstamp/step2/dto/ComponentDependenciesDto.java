package microstamp.step2.dto;

import lombok.Data;
import microstamp.step2.data.Component;
import microstamp.step2.data.Connection;
import microstamp.step2.data.Variable;

import java.util.ArrayList;
import java.util.List;

@Data
public class ComponentDependenciesDto {
    List<Component> components = new ArrayList<>();
    List<Connection> connections = new ArrayList<>();
    List<Variable> variables = new ArrayList<>();
}
