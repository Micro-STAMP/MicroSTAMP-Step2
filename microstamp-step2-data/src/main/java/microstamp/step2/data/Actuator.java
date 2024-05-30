package microstamp.step2.data;

import jakarta.persistence.Entity;

@Entity
public class Actuator extends Component{

    public Actuator(){super();}

    public Actuator(long id, String name, boolean isVisible, Component father, Style border) {
        super(id, name, isVisible, father, border);
    }

    @Override
    public void verify() {
        System.out.println("This is an Actuator");
    }

}
