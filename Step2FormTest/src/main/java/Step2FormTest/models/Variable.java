package Step2FormTest.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Variable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "variable_id")
    private List<State> states;

    public Variable() {
    }

    public Variable(long id, String name, List<State> states) {
        this.id = id;
        this.name = name;
        this.states = states;
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

    public List<State> getStates() {
        return states;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }
}
