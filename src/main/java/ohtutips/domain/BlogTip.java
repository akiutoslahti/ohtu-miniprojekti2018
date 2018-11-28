package ohtutips.domain;

import javax.persistence.Entity;

@Entity
public class BlogTip extends Tip {

    private String url;

    public void setURL(String url) {
        this.url = url;
    }

    public String getURL() {
        return url;
    }

}
