package microstamp.step2.data;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "Label")
@Table(name = "labels")
@Data
public class Label {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String label;

}
