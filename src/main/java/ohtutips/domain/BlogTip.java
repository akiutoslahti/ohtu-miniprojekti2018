package ohtutips.domain;

import javax.persistence.Entity;

@Entity
public class BlogTip extends Tip {

    private String url;

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

}
