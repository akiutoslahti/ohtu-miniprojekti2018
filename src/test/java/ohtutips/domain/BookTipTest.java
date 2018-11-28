package ohtutips.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

public class BookTipTest {

    private BookTip testBook;

    @Before
    public void setUp() {
        this.testBook = new BookTip();
    }

    @Test
    public void setGetIsbnTest() {
        assertNull(this.testBook.getIsbn());
        this.testBook.setIsbn("test isbn");
        assertEquals("test isbn", this.testBook.getIsbn());
    }

}
