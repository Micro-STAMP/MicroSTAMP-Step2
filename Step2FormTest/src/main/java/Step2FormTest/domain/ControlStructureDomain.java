package Step2FormTest.domain;

import Step2FormTest.models.Component;
import Step2FormTest.models.Connection;

import java.util.List;

public class ControlStructureDomain{

    private List<Component> components_ids;

    private List<Connection> connections_ids;

    private String name;

    public List<Component> getComponents_ids() {
        return components_ids;
    }

    public void setComponents_ids(List<Component> components_ids) {
        this.components_ids = components_ids;
    }

    public List<Connection> getConnections_ids() {
        return connections_ids;
    }

    public void setConnections_ids(List<Connection> connections_ids) {
        this.connections_ids = connections_ids;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
