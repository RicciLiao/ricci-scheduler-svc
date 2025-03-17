package ricciliao.scheduler.pojo.dto;

import java.io.Serializable;

public class InnerMateDataDto implements Serializable {

    private static final long serialVersionUID = -2445963043885329758L;
    private String endpoint;
    private String description;

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
