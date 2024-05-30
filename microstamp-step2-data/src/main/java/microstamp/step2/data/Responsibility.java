package microstamp.step2.data;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "Responsibility")
@Table(name = "responsibilities")
@Data
public class Responsibility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String responsibility;

    private String systemSafetyConstraintAssociated;

}
