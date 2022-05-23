package Step2FormTest.domain;

public class ResponsibilityDomain {

    private Long id;

    private String responsibility;

    private Long component_id;

    public ResponsibilityDomain() {
    }

    public ResponsibilityDomain(Long id, String responsibility, Long component_id) {
        this.id = id;
        this.responsibility = responsibility;
        this.component_id = component_id;
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
}
