package Step2FormTest.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ControlStructure extends Component{

    private static ControlStructure controlStructure;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "controlStructure_id")
    private List<Component> components;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "controlStructure_id")
    private List<Connection> connections;

    private ControlStructure(long id, int layer, String name, boolean isVisible, Component father) {
        super(id, layer, name, isVisible, father,null);
        components = new ArrayList<>();
        connections= new ArrayList<>();
    }

    public static ControlStructure getInstance(long id, int layer, String name, boolean isVisible, Component father){
        if(controlStructure == null)
            controlStructure = new ControlStructure(id, layer, name, isVisible, father);
        return controlStructure;
    }

    public void addComponent(Component component){
        this.components.add(component);
    }

    public void addConnection(Connection connection){
        this.connections.add(connection);
    }

    @Override
    public void verify() {
        System.out.println("This is a ControlStructure");
    }

    @Override
    public void show(){
        for(int i = 0;i<components.size();i++){
            components.get(i).show();
            System.out.println();
        }

        for(int i = 0;i<connections.size();i++){
            System.out.println(connections.get(i));
            System.out.println();
        }
    }

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(ArrayList<Component> components) {
        this.components = components;
    }

    public List<Connection> getConnections() {
        return connections;
    }

    public void setConnections(List<Connection> connections) {
        this.connections = connections;
    }
}
