package Step2FormTest.models;

import javax.persistence.Entity;

@Entity
public class Actuator extends Component{

    //teste pra commit do git :D
    //new teste :D

    public Actuator(){super();}

    public Actuator(long id, int layer, String name, boolean isVisible, Component father, Border border) {
        super(id, layer, name, isVisible, father, border);
    }

    @Override
    public void verify() {
        System.out.println("This is an Actuator");
    }

}
