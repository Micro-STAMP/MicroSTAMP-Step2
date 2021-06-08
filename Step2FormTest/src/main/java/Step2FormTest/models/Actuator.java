package Step2FormTest.models;

import javax.persistence.Entity;

@Entity
public class Actuator extends Component{

    //teste pra commit do git :D
    //new teste :D
    //teste de novo :(

    public Actuator(){super();}

    public Actuator(long id, String name, boolean isVisible, Component father, Style border) {
        super(id, name, isVisible, father, border);
    }

    @Override
    public void verify() {
        System.out.println("This is an Actuator");
    }

}
