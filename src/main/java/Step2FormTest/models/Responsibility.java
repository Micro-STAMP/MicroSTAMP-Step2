package Step2FormTest.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Responsibility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String responsibility;

    private String systemSafetyConstraintAssociated;

    public Responsibility() {
    }

    public Responsibility(String responsibility) {
        this.responsibility = responsibility;
    }

    public Responsibility(String responsibility, String systemSafetyConstraintAssociated) {
        this.responsibility = responsibility;
        this.systemSafetyConstraintAssociated = systemSafetyConstraintAssociated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResponsibility() {
        return responsibility;
    }

    public void setResponsibility(String responsibility) {
        this.responsibility = responsibility;
    }

    public String getSystemSafetyConstraintAssociated() {
        return systemSafetyConstraintAssociated;
    }

    public void setSystemSafetyConstraintAssociated(String systemSafetyConstraintAssociated) {
        this.systemSafetyConstraintAssociated = systemSafetyConstraintAssociated;
    }
}
