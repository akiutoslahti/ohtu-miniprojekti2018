package ohtutips.domain;

import javax.persistence.Entity;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class BookTip extends AbstractPersistable<Long> {
    
    private String author;
    private String title;
    private String Type;
    private String isbn;
    
    // Will be replaced when the database is complete
    private String tags;
    private String prerequisiteCourses;
    private String relatedCourses;

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

    public void setPrerequisiteCourses(String prerequisiteCourses) {
        this.prerequisiteCourses = prerequisiteCourses;
    }

    public void setRelatedCourses(String relatedCourses) {
        this.relatedCourses = relatedCourses;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getPrerequisiteCourses() {
        return prerequisiteCourses;
    }

    public String getRelatedCourses() {
        return relatedCourses;
    }

    public String getTags() {
        return tags;
    }

    public String getType() {
        return Type;
    }
    
}
