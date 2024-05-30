package microstamp.step2.data;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "State")
@Table(name = "states")
@Data
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

}
