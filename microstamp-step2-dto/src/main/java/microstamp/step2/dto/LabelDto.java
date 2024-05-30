package microstamp.step2.dto;

public class LabelDto {

    private Long id;
    private String label;
    private Long connection_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Long getConnection_id() {
        return connection_id;
    }

    public void setConnection_id(Long connection_id) {
        this.connection_id = connection_id;
    }
}
