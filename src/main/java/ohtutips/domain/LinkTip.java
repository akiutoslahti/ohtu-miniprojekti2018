package ohtutips.domain;

import javax.persistence.Entity;

@Entity
public class LinkTip extends Tip {

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
