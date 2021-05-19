package Step2FormTest.models;

import javax.persistence.Entity;

@Entity
public class Sensor extends Component{

    public Sensor(){super();}

    public Sensor(long id, int layer, String name, boolean isVisible, Component father, Border border) {
        super(id, layer, name, isVisible, father,border);
    }

    @Override
    public void verify() {
        System.out.println("This is a Sensor");
    }

}
