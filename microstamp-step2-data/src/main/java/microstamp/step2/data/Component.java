package microstamp.step2.data;

import jakarta.persistence.*;
import java.util.List;

@Entity
public abstract class Component {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private Boolean isVisible;
    private Style border;
    private Boolean isControlStructure;

    @ManyToOne
    @JoinColumn(name="father_id")
    private Component father;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "component_id")
    private List<Variable> variables;

    public Component(){
        isControlStructure = false;
    }

    public Component(long id, String name, boolean isVisible, Component father, Style border) {
        this.id = id;
        this.name = name;
        this.isVisible = isVisible;
        this.father = father;
        this.border = border;
    }

    public Component(long id, String name, Boolean isVisible, Style border, Boolean isControlStructure, Component father, List<Variable> variables) {
        this.id = id;
        this.name = name;
        this.isVisible = isVisible;
        this.border = border;
        this.isControlStructure = isControlStructure;
        this.father = father;
        this.variables = variables;
    }

    public void show(){
        System.out.println("Name:.." + this.name);
        System.out.println("Style:.."+ this.border);
        if(this.father == null)
            System.out.println("Father:.. null");
        else
            System.out.println("Father:.." + this.father.name);
    }

    public String getName() {
        return name;
    }

    public abstract void verify();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(Boolean visible) {
        isVisible = visible;
    }

    public Component getFather() {
        return father;
    }

    public void setFather(Component father) {
        this.father = father;
    }

    public Style getBorder() {
        return border;
    }

    public void setBorder(Style border) {
        this.border = border;
    }

    public Boolean getControlStructure() {
        return isControlStructure;
    }

    public void setControlStructure(Boolean controlStructure) {
        isControlStructure = controlStructure;
    }

    public List<Variable> getVariables() {
        return variables;
    }

    public void setVariables(List<Variable> variables) {
        this.variables = variables;
    }

    public String getType(){
        String s = this.getClass().getName();
        String[] split = s.split("models.");
        return split[split.length - 1];
    }

    public String getFatherName(){
        if(father == null)
            return "null";
        else
            return father.getName();
    }

}
