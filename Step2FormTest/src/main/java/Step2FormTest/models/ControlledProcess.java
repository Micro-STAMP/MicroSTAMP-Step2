package Step2FormTest.models;

import javax.persistence.Entity;

@Entity
public class ControlledProcess extends Component{

    public ControlledProcess(){super();}

    public ControlledProcess(long id, int layer, String name, boolean isVisible, Component father, Style border) {
        super(id, layer, name, isVisible, father,border);
    }

    @Override
    public void verify() {
        System.out.println("This is a ControlledProcess");
    }

}
