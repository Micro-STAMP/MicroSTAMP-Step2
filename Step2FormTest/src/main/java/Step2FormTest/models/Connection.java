package Step2FormTest.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Connection {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private ConnectionType connectionType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="source_id")
    private Component source;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="target_id")
    private Component target;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "connection_id")
    private List<Label> labels;

    public Connection(){}

    public Connection(long id, ConnectionType connectionType, Component source, Component target) {
        this.id = id;
        this.connectionType = connectionType;
        this.source = source;
        this.target = target;
        this.labels = new ArrayList<>();
    }

    public void addLabel(Label label){
        labels.add(label);
    }

    public String printLabel(){
        String print = "";
        for(int i=0; i<this.labels.size();i++)
            if(labels.get(i)!=null)
                print = print + labels.get(i).toString()+", ";
            else print = print + "null";
        return print;
    }

    @Override
    public String toString() {
        String print = "";
        if(this.source == null)
            return "Connection{" + "Id=" + id + ", ConnectionType=" + connectionType +", Labels="+ printLabel() + " Source= null" +", Target=" + target.getName() + '}';
        if(this.target==null)
            return "Connection{" + "Id=" + id + ", ConnectionType=" + connectionType +", Labels="+ printLabel() + " Source=" + source.getName() +", Target= null"+ '}';
        return "Connection{" + "Id=" + id + ", ConnectionType=" + connectionType +", Labels="+ printLabel() + " Source=" + source.getName() +", Target=" + target.getName() + '}';

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ConnectionType getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(ConnectionType connectionType) {
        this.connectionType = connectionType;
    }

    public Component getSource() {
        return source;
    }

    public void setSource(Component source) {
        this.source = source;
    }

    public Component getTarget() {
        return target;
    }

    public void setTarget(Component target) {
        this.target = target;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }
}
