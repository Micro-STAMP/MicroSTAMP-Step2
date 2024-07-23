package microstamp.step2.data;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "ControlledProcess")
@Getter
@Setter
public class ControlledProcess extends Component {

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "component_id")
    private List<Responsibility> responsibilities = new ArrayList<>();

}
