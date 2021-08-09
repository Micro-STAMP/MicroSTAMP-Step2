package Step2FormTest.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ControlStructure{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "controlStructure_id")
    private List<Component> components;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "controlStructure_id")
    private List<Connection> connections;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "controlStructure_id")
    private List<Image> images;

    public ControlStructure() {
        components = new ArrayList<>();
        connections= new ArrayList<>();
    }

    public void addComponent(Component component){
        this.components.add(component);
    }

    public void addConnection(Connection connection){
        this.connections.add(connection);
    }

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }

    public List<Connection> getConnections() {
        return connections;
    }

    public void setConnections(List<Connection> connections) {
        this.connections = connections;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
