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
    @JoinColumn(name="destination_id")
    private Component destination;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "connection_id")
    private List<Label> labels;

    public Connection(){}

    public Connection(long id, ConnectionType connectionType, Component source, Component destination) {
        this.id = id;
        this.connectionType = connectionType;
        this.source = source;
        this.destination = destination;
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
            return "Connection{" + "Id=" + id + ", ConnectionType=" + connectionType +", Labels="+ printLabel() + " Source= null" +", Destination=" + destination.getName() + '}';
        if(this.destination==null)
            return "Connection{" + "Id=" + id + ", ConnectionType=" + connectionType +", Labels="+ printLabel() + " Source=" + source.getName() +", Destination= null"+ '}';
        return "Connection{" + "Id=" + id + ", ConnectionType=" + connectionType +", Labels="+ printLabel() + " Source=" + source.getName() +", Destination=" + destination.getName() + '}';

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

    public Component getDestination() {
        return destination;
    }

    public void setDestination(Component destination) {
        this.destination = destination;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }
}
