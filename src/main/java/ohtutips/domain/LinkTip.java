package ohtutips.domain;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
public class LinkTip extends Tip {
    
    @NotBlank(message = "URL should not be empty")
    @URL(message = "Please use valid url for the url")
    private String url;
    private String type;
    
    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
