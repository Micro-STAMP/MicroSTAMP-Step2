package microstamp.step2.data;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "ControlStructure")
@Table(name = "control_structures")
@Data
public class ControlStructure{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "controlStructure_id")
    private List<Component> components = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "controlStructure_id")
    private List<Connection> connections = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "controlStructure_id")
    private List<Image> images = new ArrayList<>();

    public void addComponent(Component component){
        this.components.add(component);
    }

    public void addConnection(Connection connection){
        this.connections.add(connection);
    }

}
