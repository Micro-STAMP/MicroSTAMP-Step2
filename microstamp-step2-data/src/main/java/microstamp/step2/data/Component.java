package microstamp.step2.data;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Component")
@Table(name = "components")
@Data
public abstract class Component {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private Boolean isVisible;
    private Style border;
    private Boolean isControlStructure;

    @ManyToOne
    @JoinColumn(name = "father_id")
    private Component father;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "component_id")
    private List<Variable> variables = new ArrayList<>();

    public String getType() {
        String s = this.getClass().getName();
        String[] split = s.split("models.");
        return split[split.length - 1];
    }
}
