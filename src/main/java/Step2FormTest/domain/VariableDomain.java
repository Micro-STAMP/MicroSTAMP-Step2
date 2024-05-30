package Step2FormTest.domain;

public class VariableDomain {

    private Long id;

    private String name;

    private Long component_id;

    public VariableDomain() {
    }

    public VariableDomain(Long id, String name, Long component_id) {
        this.id = id;
        this.name = name;
        this.component_id = component_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getComponent_id() {
        return component_id;
    }

    public void setComponent_id(Long component_id) {
        this.component_id = component_id;
    }
}
