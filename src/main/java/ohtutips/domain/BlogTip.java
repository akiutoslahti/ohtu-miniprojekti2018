package ohtutips.domain;

import javax.persistence.Entity;
import org.hibernate.validator.constraints.URL;

@Entity
public class BlogTip extends Tip {
    
    @URL(message = "Please use valid url for the url")
    private String url;

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

}
