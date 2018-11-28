package ohtutips.domain;

import javax.persistence.Entity;

@Entity
public class BookTip extends Tip {

    private String isbn;

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }


}
