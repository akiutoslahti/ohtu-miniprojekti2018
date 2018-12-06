package ohtutips.domain;

import javax.persistence.Entity;
import javax.validation.constraints.Size;

@Entity
public class BookTip extends Tip {
    
    @Size(min = 10, max = 14, message = "ISBN should not be less than 10 characters or more than 14 characters")
    private String isbn;

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }

}
