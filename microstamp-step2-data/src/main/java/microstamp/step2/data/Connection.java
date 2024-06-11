package microstamp.step2.data;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Connection")
@Table(name = "connections")
@Data
public class Connection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private ConnectionType connectionType;

    @ManyToOne
    @JoinColumn(name = "source_id")
    private Component source;

    @ManyToOne
    @JoinColumn(name = "target_id")
    private Component target;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "connection_id")
    private List<Label> labels = new ArrayList<>();

    private Style style;

    public void addLabel(Label label) {
        labels.add(label);
    }

    public String getSourceName(){
        return source.getName();
    }

    public String getTargetName(){
        return target.getName();
    }
}
