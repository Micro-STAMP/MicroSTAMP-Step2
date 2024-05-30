package microstamp.step2.data;

import jakarta.persistence.Entity;

@Entity
public class Environment extends Component{

    public Environment(){
        super();
        this.setName("Environment");
        this.setIsVisible(false);
        this.setBorder(Style.SOLID);
        this.setFather(null);
    }
}
