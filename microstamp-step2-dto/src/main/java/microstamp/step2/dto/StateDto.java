package microstamp.step2.dto;

public class StateDto {

    private Long id;

    private String name;

    private Long variable_id;

    public StateDto() {
    }

    public StateDto(Long id, String name, Long variable_id) {
        this.id = id;
        this.name = name;
        this.variable_id = variable_id;
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

    public Long getVariable_id() {
        return variable_id;
    }

    public void setVariable_id(Long variable_id) {
        this.variable_id = variable_id;
    }
}
