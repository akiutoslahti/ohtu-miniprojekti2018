package ohtutips.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class BookTip extends AbstractPersistable<Long> {
    
    private String author;
    private String title;
    private String Type;
    private String isbn;
    
    // Will be replaced when the database is complete
    private List<String> tags = new ArrayList<>();
    private List<String> prerequisiteCourses = new ArrayList<>();;
    private List<String> relatedCourses = new ArrayList<>();;

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

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setPrerequisiteCourses(List<String> prerequisiteCourses) {
        this.prerequisiteCourses = prerequisiteCourses;
    }

    public void setRelatedCourses(List<String> relatedCourses) {
        this.relatedCourses = relatedCourses;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getIsbn() {
        return isbn;
    }

    public List<String> getPrerequisiteCourses() {
        return prerequisiteCourses;
    }

    public List<String> getRelatedCourses() {
        return relatedCourses;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getType() {
        return Type;
    }
    
}
