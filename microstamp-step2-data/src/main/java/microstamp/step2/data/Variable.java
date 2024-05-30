package microstamp.step2.data;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Variable")
@Table(name = "variables")
@Data
public class Variable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "variable_id")
    private List<State> states = new ArrayList<>();

}
