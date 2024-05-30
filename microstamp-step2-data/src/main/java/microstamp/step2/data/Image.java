package microstamp.step2.data;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "Image")
@Table(name = "images")
@Data
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

}
