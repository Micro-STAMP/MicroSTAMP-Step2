package Step2FormTest.models;

import javax.persistence.Entity;

@Entity
public class Controller extends Component{

    public Controller(){super();}
    public Controller(long id, String name, boolean isVisible, Component father, Style border) {
        super(id, name, isVisible, father,border);
    }

    @Override
    public void verify() {
        System.out.println("This is a Controller");
    }

}
