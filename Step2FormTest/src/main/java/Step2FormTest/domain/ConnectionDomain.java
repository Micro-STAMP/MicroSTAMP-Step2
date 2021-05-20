package Step2FormTest.domain;

import Step2FormTest.models.ConnectionType;

import java.util.List;

public class ConnectionDomain {

    private Long id;
    private ConnectionType connectionType;
    private String source_id;
    private String destination_id;
    private List<String> labels_ids;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ConnectionType getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(ConnectionType connectionType) {
        this.connectionType = connectionType;
    }

    public String getSource_id() {
        return source_id;
    }

    public void setSource_id(String source_id) {
        this.source_id = source_id;
    }

    public String getDestination_id() {
        return destination_id;
    }

    public void setDestination_id(String destination_id) {
        this.destination_id = destination_id;
    }

    public List<String> getLabels_ids() {
        return labels_ids;
    }

    public void setLabels_ids(List<String> labels_ids) {
        this.labels_ids = labels_ids;
    }
}
