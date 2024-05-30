package microstamp.step2.dto;

public class ResponsibilityDto {

    private Long id;

    private String responsibility;

    private Long component_id;

    private String systemSafetyConstraintAssociated;

    public ResponsibilityDto() {
    }

    public ResponsibilityDto(Long id, String responsibility, Long component_id, String systemSafetyConstraintAssociated) {
        this.id = id;
        this.responsibility = responsibility;
        this.component_id = component_id;
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

    public Long getComponent_id() {
        return component_id;
    }

    public void setComponent_id(Long component_id) {
        this.component_id = component_id;
    }

    public String getSystemSafetyConstraintAssociated() {
        return systemSafetyConstraintAssociated;
    }

    public void setSystemSafetyConstraintAssociated(String systemSafetyConstraintAssociated) {
        this.systemSafetyConstraintAssociated = systemSafetyConstraintAssociated;
    }
}
