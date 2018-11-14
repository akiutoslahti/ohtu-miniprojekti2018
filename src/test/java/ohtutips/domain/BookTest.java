package ohtutips.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BookTest {

    private Book testBook;

    @Before
    public void setUp() {
        this.testBook = new Book();
    }

    @Test
    public void setGetTitleTest() {
        assertNull(this.testBook.getTitle());
        this.testBook.setTitle("test title");
        assertEquals("test title", this.testBook.getTitle());
    }

    @Test
    public void setGetAuthorTest() {
        assertNull(this.testBook.getAuthor());
        this.testBook.setAuthor("test author");
        assertEquals("test author", this.testBook.getAuthor());
    }

}
