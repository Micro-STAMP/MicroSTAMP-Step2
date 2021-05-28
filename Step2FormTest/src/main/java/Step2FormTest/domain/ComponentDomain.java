package Step2FormTest.domain;

import Step2FormTest.models.Border;

public abstract class ComponentDomain {

    private Long id;
    private Integer layer;
    private String name;
    private Boolean isVisible;
    private Long father_id;
    private Border border;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLayer() {
        return layer;
    }

    public void setLayer(Integer layer) {
        this.layer = layer;
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

    public Border getBorder() {
        return border;
    }

    public void setBorder(Border border) {
        this.border = border;
    }

    public Boolean getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(Boolean visible) {
        isVisible = visible;
    }
}
