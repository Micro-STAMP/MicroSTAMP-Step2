package Step2FormTest.models;

import javax.persistence.Entity;

@Entity
public class Environment extends Component{

    public Environment(){
        super();
        this.setName("Environment");
        this.setIsVisible(false);
        this.setBorder(Style.SOLID);
        this.setFather(null);
    }

    @Override
    public void verify(){ System.out.println("This is a special Component called Environment"); }
}
