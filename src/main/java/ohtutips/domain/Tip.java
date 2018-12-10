package ohtutips.domain;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.springframework.data.jpa.domain.AbstractPersistable;

@MappedSuperclass
public abstract class Tip extends AbstractPersistable<Long> {
    
    @NotBlank(message = "Author should not be empty")
    @Size(max = 200, message = "Author should be less than 200 characters long")
    private String author;
    
    @NotBlank(message = "Title should not be empty")
    @Size(max = 200, message = "Title should be less than 200 characters long")
    private String title;
    private boolean studied;
    
    @NotBlank(message = "Tags should not be empty")
    @Size(max = 200, message = "Tags should be less than 200 characters long")
    private String tags;
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean getStudied() {
        return studied;
    }

    public void setStudied(boolean studied) {
        this.studied = studied;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getTags() {
        return tags;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
