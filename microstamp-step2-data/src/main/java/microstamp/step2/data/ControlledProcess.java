package microstamp.step2.data;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "ControlledProcess")
@Getter
@Setter
public class ControlledProcess extends Component{

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "component_id")
    private List<Responsibility> responsibilities = new ArrayList<>();

}
