package Step2FormTest.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class ControlledProcess extends Component{

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "component_id")
    private List<Responsibility> responsibilities;

    public ControlledProcess(){super();}

    public ControlledProcess(long id, String name, boolean isVisible, Component father, Style border) {
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
        System.out.println("This is a ControlledProcess");
    }

}
