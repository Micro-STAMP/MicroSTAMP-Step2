package Step2FormTest.models;

import javax.persistence.*;

@Entity
public abstract class Component {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int layer;
    private String name;
    private boolean isVisible;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="father_id")
    private Component father;

    private Border border;

    public Component(){}
    public Component(long id, int layer, String name, boolean isVisible, Component father, Border border) {
        this.id = id;
        this.layer = layer;
        this.name = name;
        this.isVisible = isVisible;
        this.father = father;
        this.border = border;
    }

    public void show(){
        System.out.println("Name:.." + this.name);
        System.out.println("Layer:.." + this.layer);
        System.out.println("Border:.."+ this.border);
        if(this.father == null)
            System.out.println("Father:.. null");
        else
            System.out.println("Father:.." + this.father.name);
    }

    public String getName() {
        return name;
    }

    public boolean isIsVisible(){
        return isVisible;
    }

    public abstract void verify();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getLayer() {
        return layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public Component getFather() {
        return father;
    }

    public void setFather(Component father) {
        this.father = father;
    }

    public Border getBorder() {
        return border;
    }

    public void setBorder(Border border) {
        this.border = border;
    }
}
