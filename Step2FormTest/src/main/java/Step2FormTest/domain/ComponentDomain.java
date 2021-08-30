package Step2FormTest.domain;

import Step2FormTest.models.Style;

public abstract class ComponentDomain {

    private Long id;
    private String name;
    private Boolean isVisible;
    private Long father_id;
    private Long control_structure_id;
    private Style border;
    private String type;

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

    public Long getFather_id() {
        return father_id;
    }

    public void setFather_id(Long father_id) {
        this.father_id = father_id;
    }

    public Style getBorder() {
        return border;
    }

    public void setBorder(Style border) {
        this.border = border;
    }

    public Boolean getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(Boolean visible) {
        isVisible = visible;
    }

    public Long getControl_structure_id() {
        return control_structure_id;
    }

    public void setControl_structure_id(Long control_structure_id) {
        this.control_structure_id = control_structure_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
