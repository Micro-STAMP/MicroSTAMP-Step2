package microstamp.step2.data;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class Controller extends Component{

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "component_id")
    private List<Responsibility> responsibilities;

    public Controller(){super();}

    public Controller(long id, String name, boolean isVisible, Component father, Style border) {
        super(id, name, isVisible, father,border);
    }

    public List<Responsibility> getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(List<Responsibility> responsibilities) {
        this.responsibilities = responsibilities;
    }

    @Override
    public void verify() {
        System.out.println("This is a Controller");
    }

}
